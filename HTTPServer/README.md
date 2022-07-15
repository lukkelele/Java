## http server
---

The program is not reliant on any other files other than itself, HTTPServer.java.  
The predefined port is set to port 8888.  
Starting the server can be done in multiple ways because of the error handling regarding the command line arguments.  
Simply running the program without any arguments will raise an error and give output on how to execute properly.\\
At least one argument is needed for the server to actually start. If the argument(s) are incorrect, either in position or in amount (3 or more), then a default preset will be applied.  
Starting the server in the terminal will look like -> **java HTTPServer {PORT} {DIRECTORY}**  
With the provided error handling, server starts can look like:
- _java HTTPServer 8882 /public/a/b_ ==> if the port is available and the directory exists the values entered will be the server values
- _java HTTPServer dir 8218 k23_     ==> this will start the server with the default values for the port and directory
- _java HTTPSerer public 8888_       ==> because the port and path arguments are misplaced, the default value is selected

If a port is already in use then a bindexception will get thrown and the user is told to try another port.  

---
As far as I've tested, there are no issues with execution.   
It is not possible to traverse the directory by setting a relative path such as _'../../../../../../'_ or just _'/etc'_.  
