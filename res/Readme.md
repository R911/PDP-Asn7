## Modifications for Assignment 9

The code contains the implementation of a complete Image processing application that supports the following features:
1. Blur/Sharpen
2. Sepia/Greyscale.
3. Image dithering.
4. Image mosaic.
5. Draw Rainbow/Flag/Checkerboard.

The functions listed above can be used both in a script mode or an interactive GUI mode.

### Controller:
1. Added a new interface Features.java, and it's implementation ExtendedControllerImpl.java. The ExtendedControllerImpl
also implements the existing Controller interface.

### View:
A complete GUI implementation was added using Java Swing. The summary of the view implementation can be found below.

1. Added interface View.java and its implementation ViewImpl.java were added in the view package.
2. Action.java and ActionListener.java for event handling were added in view.actions package.
3. The various UI components are added in the view.components package. These include widgets for menu operations,
 dialog box implementation, file picker and scrollable image viewer.
4. Other small packages containing utils and constants.

## How to use:
The jar file for the complete Image processing application is enclosed in the res directory.
The following modes are available:
1. Script based mode: Run the jar file as follows:
*java -jar CS5010-Assignment-7.jar -script fully_qualified_script_path*

2. Interactive GUI mode:
*java -jar CS5010-Assignment-7.jar -interactive*



## README for Assignments 7 and 8
This program will be based on a MVC model and currently the Model part is implemented (as specified). 
Other than that our program has common dto (data transfer objects) objects (which will be used by the entire program, enums, 
factory(to be used by controller to provide filters and transformers) and IO classes for reading and writing images.

The program currently runs from the Main.java file in the src folder. In its main method, an ExtendedModel object is instantiated. 
This model provides the following methods:

Image applyTransform(float[][] transformArr, Image image);
<br />
Image applyFilter(float[][] filterArr, Image image);
<br />
Image drawCheckerBoard(int boardSize, int squareSize);
<br />
Image drawRainbow(int width, int height, int stripeSize, char orientation, boolean repeat);
<br />
Image drawFlag(FlagEnum countryName, int flagWidth);
<br />
Image applyDither(float[][] ditherKernel, Image image);
<br />
Image applyMosaic(int seedCount, Image image);

The entirety of our program is complete (based upon the requirements provided in Assignment 8). This includes all the work for 
extra credit(mosaic images). The program currently supports only RGB/RGBA models and support for other 
color models is still pending (not a requirement).


Assignment 8 change log:
The following design changes were made from Assignment 7 for Assignment 8.


### Model: 
The following classes/interfaces were added to the model implementation. Except for one place (mentioned below as NOTE), 
the existing interface have not been modified.

1. ExtendedModel(extends Model interface) interface added to support the following methods: 
- Image applyDither(float[][] ditherKernel, Image image);
- Image applyMosaic(int seedCount, Image image);
The ExtendedModel interface extends the capabilities of the original Model interface by adding dither/mosaic capabilities. We have chosen 
not to modify the original Model interface for backward compatibility.

2. ExtendedModelImpl (implements ExtendedModel, extends ModelImpl) is added as a concrete implementation of the ExtendedModel interface.

3. ExtendedImageModel(extends ImageModel interface) interface added support for the following methods:
- Canvas applyDither(Dither dither, Canvas canvas);
- Canvas applyMosaic(int seedCount, Canvas canvas);
NOTE: Method signatures in ImageModel interface are modified to take a canvas and return a canvas instead of an image. 
Since these methods only work on the canvas (which is used inside Image) and not on other variables stored in the image, 
the image object not needed. The ExtendedImageModel adds dither/mosaic capability on top of the ones provided by ImageModel interface.

4. ExtendedImageModelImpl (implements ExtendedImageModel, extends ImageModel) is added as a concrete implementation.

5. Dither and Mosaic packages are added to model.image package.
Dither package has Dither(Interface) and DitherImpl(Class)
Mosaic package has Mosaic(Interface), MosaicImpl(Class), MosaicPixel(Class), MosaicTile(Interface) and SimpleMosaicTile(Class)
These classes contain the core implementation of the dither and mosaic requirements.

### Factory:
dither package is added, and a DitherKernelFactory class it added. It is used to get kernel for Floyd Steinberg Dither or 
JJNDither (extra, not part of the assignment requirement).

### Enum:
DitherEnum Added, containing the supported dither Kernels.

### Controller:
1. controller package added, containing the classes Controller(Interface), ControllerImpl (implementation of the controller interface).
The Controller interface provides methods to the clients to use the controller.

2. command Package added, containing the classes ImageCommand(Interface), ReadCommand(Interface) and WriteCommand (interface).
Each of these interfaces represents a type of command. The ImageCommand interface represents the commands that produce an image,
the ReadCommand interface represents commands for read I/O, and similarly WriteCommand interface represents commands for write I/O.
Concrete implementations of the above interfaces are added to command.draw, command.process and command.io packages.

3. We have used the command design pattern to implement the controller loop which reads a sequence of commands from the input 
and executes them.


### Bug Fixes:
1. Rainbow Implementation modified for reduced code duplication and helper methods added.
2. Missing Null and type checks added.
3. Flag implementation has less cumbersome logic for calculating width and height of flag given the user input.


### How to use:
The program is packaged as a .jar file. The main method of the jar expects a single file path as input. Additionally, 
to test the jar using the command line, instead of providing the file path as the input to the jar, the string "in" must be provided. 
If that option is selected, the program expects inputs from the standard input (which is usually the terminal from where the jar was run).

#### Commands:
There are 2 types of image processing commands:
1. process <image_processing_operation>: The process prefix is to be appended to any operation that takes an image and returns an image. So,
to apply sepia on an image, first read the image using the read command, followed by the following: "process sepia". The same applies for each
of the operations.
2. draw <what_to_draw> <inputs>: The draw command command expects the pattern to draw and the extra inputs needed.
<br />
PLEASE NOTE: The scripts provided with the submission contain local file paths for our machines. Running them as-is will most certainly cause
issues due to files/directories not being where they're supposed to be. To run the scripts, kindly substitute the file paths with valid
file paths of the machine where the code is being run. Please consult script1.txt and script2.txt for more examples.
<br />

### Citations
All pictures in this assignment description are photographs taken by Rahul Singh, or results generated by a program written 
by Ankit Kshatriya and Rahul Singh. Ankit Kshatriya and Rahul Singh retains the ownership of these photographs and prohibits 
any use or modification by others.
