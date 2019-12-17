#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/uaccess.h>
#include <linux/fs.h>
#include <linux/miscdevice.h>
#include <asm/ioctl.h>
#include <linux/delay.h>

#define DRIVER_AUTHOR	"CAUSW JH"
#define DRIVER_DESC	"driver for dotmatrix"

#define DOTM_NAME		"dotmatrix"
#define DOTM_MOUDLE_VERSION	"dotmatrix V2.0"
#define DOTM_ADDR		0x210

#define DOTM_MAGIC 0xBC
#define DOTM_SET_ALL		_IOW(DOTM_MAGIC, 0, int)
#define DOTM_SET_CLEAR		_IOW(DOTM_MAGIC, 1, int)
#define DOTM_EXPLOSION1		_IOW(DOTM_MAGIC, 2, int)
#define DOTM_EXPLOSION2		_IOW(DOTM_MAGIC, 3, int)
#define DOTM_EXPLOSION3		_IOW(DOTM_MAGIC, 4, int)
#define DOTM_EXPLOSION4		_IOW(DOTM_MAGIC, 5, int)
#define DOTM_EXPLOSION5		_IOW(DOTM_MAGIC, 6, int)
#define DOTM_EXPLOSION6		_IOW(DOTM_MAGIC, 7, int)
#define DOTM_EXPLOSION7		_IOW(DOTM_MAGIC, 8, int)


// gpio fpga interface provided
extern ssize_t iom_fpga_itf_read(unsigned int addr);
extern ssize_t iom_fpga_itf_write(unsigned int addr, unsigned int value);

// global
static int dotm_in_use = 0;
static char buf[20];
static unsigned char sign[8][10];

// dotmatrix fonts
unsigned char dotm_fontmap_decimal[10][10] = { 
        {0x3e,0x7f,0x63,0x73,0x73,0x6f,0x67,0x63,0x7f,0x3e}, // 0
        {0x0c,0x1c,0x1c,0x0c,0x0c,0x0c,0x0c,0x0c,0x0c,0x1e}, // 1
        {0x7e,0x7f,0x03,0x03,0x3f,0x7e,0x60,0x60,0x7f,0x7f}, // 2
        {0xfe,0x7f,0x03,0x03,0x7f,0x7f,0x03,0x03,0x7f,0x7e}, // 3
        {0x66,0x66,0x66,0x66,0x66,0x66,0x7f,0x7f,0x06,0x06}, // 4
        {0x7f,0x7f,0x60,0x60,0x7e,0x7f,0x03,0x03,0x7f,0x7e}, // 5
        {0x60,0x60,0x60,0x60,0x7e,0x7f,0x63,0x63,0x7f,0x3e}, // 6
        {0x7f,0x7f,0x63,0x63,0x03,0x03,0x03,0x03,0x03,0x03}, // 7
        {0x3e,0x7f,0x63,0x63,0x7f,0x7f,0x63,0x63,0x7f,0x3e}, // 8
        {0x3e,0x7f,0x63,0x63,0x7f,0x3f,0x03,0x03,0x03,0x03} // 9
};

unsigned char dotm_fontmap_name_eng[8][10] = {
	{0x7e, 0x41, 0x41, 0x41, 0x7e, 0x40, 0x40, 0x40, 0x40, 0x40},	// P
	{0x1c, 0x22, 0x41, 0x41, 0x41, 0x7f, 0x41, 0x41, 0x41, 0x41},	// A
	{0x7e, 0x41, 0x41, 0x41, 0x7e, 0x50, 0x48, 0x44, 0x42, 0x41},	// R
	{0x42, 0x44, 0x48, 0x50, 0x60, 0x50, 0x48, 0x44, 0x42, 0x41}, 	// K
	{0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x01, 0x41, 0x22, 0x1c},	// J
	{0x7f, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x08, 0x7f}, 	// I
	{0x41, 0x41, 0x41, 0x41, 0x7f, 0x41, 0x41, 0x41, 0x41, 0x41}, 	// H
	{0x3e, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3e} 	// O
};

unsigned char dotm_fontmap_name_kor[3][10] = {
	{0x4a, 0x7a, 0x4b, 0x7a, 0x02, 0x7c, 0x04, 0x04, 0x04, 0x04},	//park
	{0x00, 0x00, 0x7d, 0x11, 0x29, 0x45, 0x01, 0x01, 0x00, 0x00},	//ji
	{0x00, 0x08, 0x3e, 0x08, 0x14, 0x14, 0x08, 0x08, 0x7f, 0x00}	//ho
};

unsigned char dotm_fontmap_full[10] = { 
        0x7f,0x7f,0x7f,0x7f,0x7f,0x7f,0x7f,0x7f,0x7f,0x7f
};

unsigned char dotm_fontmap_empty[10] = { 
        0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00,0x00
};

unsigned char dotm_fontmap_explosion[7][10] = {
	{0x00, 0x00, 0x00, 0x08, 0x1c, 0x08, 0x00, 0x00 ,0x00, 0x00},
	{0x00, 0x00, 0x08, 0x1c, 0x36, 0x1c, 0x08, 0x00, 0x00, 0x00},
	{0x00, 0x08, 0x1c, 0x36, 0x63, 0x63, 0x36, 0x1c, 0x08, 0x00},
	{0x18, 0x3c, 0x3e, 0x63, 0x63, 0x63, 0x63, 0x3e, 0x3c, 0x18},
	{0x3c, 0x3e, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3e, 0x3c},
	{0x3e, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x41, 0x3e},
	{0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00}
};

int dotm_open(struct inode *pinode, struct file *pfile){
	int i;
	unsigned short wordvalue;

	if(dotm_in_use != 0) {
		return -EBUSY;
	}

	dotm_in_use = 1;
	
	memset(buf, 0, sizeof(buf));
	memset(sign, 0, sizeof(sign));

	// clear the dotmatrix
	for(i=0; i<10; i++){
		wordvalue = dotm_fontmap_full[i] & 0x7F;
		iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
	}

	return 0;
}

int dotm_release(struct inode *pinod, struct file *pfile){
	dotm_in_use = 0;
	return 0;
}

ssize_t dotm_write(struct file *pinode, const char * gdata, size_t len, loff_t *off_what){
	int ret, i;
	const char * tmp = NULL;
	int  num;
	unsigned short wordvalue;
	int idx=0, divide = 1;

	tmp =gdata;

	// copy userspace into kernel space buffer
	// tmp -> buf
	ret = copy_from_user(&num, tmp, len);
	if(ret <0){
		return -EFAULT;
	}
	while(1){
		buf[idx] = num / (10000000 / divide);

		num = num % (10000000 / divide);
		idx++;
		divide *= 10;
		
		if(divide == 10000000)
			break;
	}

	buf[idx] = num;

	for(i=0; i<8; i++){
		printk("buf: %d\n", buf[i]);
	}
	for(i=0; i<10; i++){
		num = buf[0];

		// number printed for dotmatrix
		wordvalue = dotm_fontmap_decimal[num][i] & 0x7F;

		// print dotmatrix
		iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
	}
	idx++;
	
	return len;
}

static long dotm_ioctl(struct file *pinode, unsigned int cmd, unsigned long data){
	int i;
	unsigned short wordvalue;

	switch(cmd){
		case DOTM_SET_ALL:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_full[i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_SET_CLEAR:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_empty[i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_EXPLOSION1:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_explosion[0][i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_EXPLOSION2:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_explosion[1][i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_EXPLOSION3:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_explosion[2][i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_EXPLOSION4:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_explosion[3][i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_EXPLOSION5:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_explosion[4][i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_EXPLOSION6:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_explosion[5][i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;
		case DOTM_EXPLOSION7:
			for(i=0; i<10; i++){
				wordvalue = dotm_fontmap_explosion[6][i] & 0x7F;
				iom_fpga_itf_write((unsigned int) DOTM_ADDR+(i*2), wordvalue);
			}
			break;

			
	}
	return 0;	
}

static struct file_operations dotm_fops = {
	.owner	 = THIS_MODULE,
	.open	= dotm_open,
	.write	= dotm_write,
	.unlocked_ioctl = dotm_ioctl,
	.release = dotm_release,

};

static struct miscdevice dotm_driver = {
	.fops	= &dotm_fops,
	.name	= DOTM_NAME,
	.minor	= MISC_DYNAMIC_MINOR,
};

int dotm_init(void){
	misc_register(&dotm_driver);
	printk(KERN_INFO "driver: %s driver init\n", DOTM_NAME);

	return 0;
}

void dotm_exit(void){
	misc_deregister(&dotm_driver);
	printk(KERN_INFO "driver: %s driver exit\n", DOTM_NAME);
}

module_init(dotm_init);
module_exit(dotm_exit);

MODULE_AUTHOR(DRIVER_AUTHOR);
MODULE_DESCRIPTION(DRIVER_DESC);
MODULE_LICENSE("Dual BSD/GPL");
