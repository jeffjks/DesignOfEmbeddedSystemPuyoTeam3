#include <linux/module.h>
#include <linux/kernel.h>
#include <linux/init.h>
#include <linux/uaccess.h>
#include <linux/fs.h>
#include <linux/miscdevice.h>

#define DRIVER_AUTHOR	"CAUSW JH"
#define DRIVER_DESC	"driver for Button"

#define PUSH_BTN_NAME		"pushBtn"
#define PUSH_BTN_MOUDLE_VERSION	"PUSH_BUTTON V1.0"
#define PUSH_BTN_ADDR		0x050

#define PUSH_MAGIC 0xBD
#define PUSH_BTN_1		_IOW(PUSH_MAGIC, 1, int)
#define PUSH_BTN_2		_IOW(PUSH_MAGIC, 2, int)
#define PUSH_BTN_3		_IOW(PUSH_MAGIC, 3, int)
#define PUSH_BTN_4		_IOW(PUSH_MAGIC, 4, int)
#define PUSH_BTN_5		_IOW(PUSH_MAGIC, 5, int)
#define PUSH_BTN_6		_IOW(PUSH_MAGIC, 6, int)
#define PUSH_BTN_7		_IOW(PUSH_MAGIC, 7, int)
#define PUSH_BTN_8		_IOW(PUSH_MAGIC, 8, int)
#define PUSH_BTN_9		_IOW(PUSH_MAGIC, 9, int)



// gpio fpga interface provided
extern ssize_t iom_fpga_itf_read(unsigned int addr);
extern ssize_t iom_fpga_itf_write(unsigned int addr, unsigned int value);

// global
static int pushBtn_in_use = 0;
int pushBtn_open(struct inode *pinode, struct file *pfile){
	if(pushBtn_in_use != 0) {
		return -EBUSY;
	}

	pushBtn_in_use = 1;

	return 0;
}

int pushBtn_release(struct inode *pinod, struct file *pfile){
	pushBtn_in_use = 0;
	return 0;
}

ssize_t pushBtn_read(struct file *pinode, char *gdata, size_t len, loff_t *off_what){
	unsigned short button = 0;
	unsigned short wordvalue;
	char *tmp = NULL;
	int i;

	tmp = gdata;

	for(i=0; i<9; i++){
		wordvalue = iom_fpga_itf_read((unsigned int) PUSH_BTN_ADDR + (i*2)) & 0x01;
		button = (button | (wordvalue << i) ) & 0x01FF ;
	}

	printk("##################################\n");
	printk("#########%#x\n", button);
	printk("##################################\n");
	if (copy_to_user(tmp, &button, 2)) {
		return -EFAULT;
	}
	return len;

}

static long pushBtn_ioctl(struct file *pinode, unsigned int cmd, unsigned long data){

	switch(cmd){
	
	}

	return 0;

}

static struct file_operations pushBtn_fops = {
	.owner	 = THIS_MODULE,
	.open	= pushBtn_open,
	.read	= pushBtn_read,
	.release = pushBtn_release,
	.unlocked_ioctl = pushBtn_ioctl,

};

static struct miscdevice pushBtn_driver = {
	.fops	= &pushBtn_fops,
	.name	= PUSH_BTN_NAME,
	.minor	= MISC_DYNAMIC_MINOR,
};

int pushBtn_init(void){
	misc_register(&pushBtn_driver);
	printk(KERN_INFO "driver: %s driver init\n", PUSH_BTN_NAME);

	return 0;
}

void pushBtn_exit(void){
	misc_deregister(&pushBtn_driver);
	printk(KERN_INFO "driver: %s driver exit\n", PUSH_BTN_NAME);
}

module_init(pushBtn_init);
module_exit(pushBtn_exit);

MODULE_AUTHOR(DRIVER_AUTHOR);
MODULE_DESCRIPTION(DRIVER_DESC);
MODULE_LICENSE("Dual BSD/GPL");
