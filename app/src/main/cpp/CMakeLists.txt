# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
add_library( # Sets the name of the library.
        dotmatrix

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        dotmatrix.c)

add_library( # Sets the name of the library.
        led

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        led.c)

add_library( # Sets the name of the library.
        7segment

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        7segment.c)

add_library( # Sets the name of the library.
        pushBtn

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        pushBtn.c)

add_library( # Sets the name of the library.
        lcd

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).
        lcd.c)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log)

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
        dotmatrix led 7segment pushBtn lcd

        # Links the target library to the log library
        # included in the NDK.
        ${log-lib})