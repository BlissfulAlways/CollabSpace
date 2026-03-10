# CollabSpace  
  
When a company starts a new software project, the first thing they do is ask these 3 questions :  
- Question 1: Where does the code live? : A folder structure that every team member understands. A predictable layout so when you open any part of the project you immediately know where you are.  
- Question 2: What does this project need to run? : Your code will depend on other people's code. Someone needs to write down exactly what those are and what versions - Dependency File. In Java it is usually pom.xml (Maven) and in JS it is package.json.  
- Question 3: How do the two halves talk during development? : You have a backend (Java) and a frontend (React). They are two separate programs. They run on different ports. They need to know about each other. Set it up.  
  
### Java Basics :  
- Java is built on one rule: everything is an object, and every object is built from a class (Java is object-oriented language).
- Class name follows PascalCase.  
- In Java, you cannot write code that floats freely. Every single instruction must live inside a method. Every method must live inside a class. There is no exception to this.  
public class HelloWorld{  
&nbsp;&nbsp;&nbsp;&nbsp;public static void main(String[] args){  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;System.out.println("Hello world");  
&nbsp;&nbsp;&nbsp;&nbsp;}  
}  
    
- In Java, access modifiers define which classes can see or use a specific field, method, or class.  
Project/  
│  
├── package_a/  
│   ├── Parent.java (Contains the variables)  
│   └── Neighbor.java (In the same folder/package)  
│  
└── package_b/  
    ├── Child.java (In a different folder, but extends Parent)  
    └── Stranger.java (In a different folder, no relation)
- public : Any class in any folder (package_a or package_b) can see and use this. It is open to the entire project.
- protected : Visible to Neighbor.java because it is in the same folder, and to Child.java because it inherits from the Parent, even though it is in a different folder.
- default (no keyword) : Visible only to Neighbor.java. Since Child.java and Stranger.java are in a different folder (package_b), they cannot see it at all.
- private : Visible only within Parent.java. No other file in any folder can access this. It is restricted to the specific file where it is written.  
  
public static void main(String[] args)  
- This is the one line Java looks for when you say "run this."  
- static — normally to use something inside a class, you first have to build an object from that class. But Java needs to run main before any objects exist. static means this method belongs to the class itself, not to any object built from it. Java can call it directly without building anything first.  
- void — every method in Java must declare what it gives back when it finishes. void means it gives back nothing. It just runs and ends.  
- String[] args — when you run a Java program from the terminal you can pass extra words after it. String means text. [] means it is a list of them. args is just the name given to that list.  
- System — a class that represents the environment your program is running in. The operating system, the terminal, the hardware.  
- out — a specific channel inside System that points to your terminal output. The screen.  
- println — a method that takes whatever you give it, converts it to text, writes it to the screen, and moves to the next line. print does the same without moving to the next line. println means print line.

### Developement Environment : Codespaces :  
Go to github.com  
On your Profile click on New Repository  
Create a repository and make it public to use Codespaces  
Open the repo and click on green Code button  
Inside Your Codespaces click on New Codespaces  
The Codespaces opens in new window with in browser VS Code  

### Project Structure :  
Program 1 is the Java backend. It will handle saving documents, authenticating users, and managing WebSocket connections. It runs on port 8080.  
Program 2 is the React frontend. It is what users see in the browser. It runs on port 5173.  

Options :  
collabspace/  
├── backend/  
└── frontend/
backend/frontend is application layer vocabulary. It describes where each program lives in the architecture — one is the logic layer, one is the presentation layer. More specific to what we are building.  
collabspace/    
├── server/  
└── client/  
server/client is network vocabulary. It describes the relationship between the two programs — one serves, one consumes. Accurate but generic.  
We pick backend/frontend  


### Spring Boot :  
When a browser wants to load a user's documents, it sends a message to your Java program. Your Java program receives that message, figures out what is being asked, does the work, and sends a message back. Then it goes back to waiting.  
This type of program is called a server. A server as in a program whose job is to serve responses to requests.  
You need a server to listen for incoming connections on port 8080.  
To do that from scratch in pure Java you would need to write code that opens a network socket, binds it to a port, listens for incoming bytes, parses those bytes into HTTP format, figures out which method to call, calls it, formats the response back into HTTP bytes, and sends it back.  
That is hundreds of lines of infrastructure code before you write a single line of your actual logic.  
Spring Boot is a framework that has already written all of that. You only bring your logic.  
Spring Boot works on Inversion of Control : inverting who is in control of creating and connecting things.  
Spring Boot works on Dependency Injection : Spring injects the dependency you need without you constructing it yourself.  
Spring Boot is an opinionated framework built on top of Spring, opinionated part means it ships with sensible defaults so you do not configure everything from scratch.  It is used for Web Applications  

###Create a Spring Boot Project :  
Option A: Spring Initializr : A website at start.spring.io  
Option B: Create everything manually : folder structure and dependency file  

pom.xml :  
When you write Java code you will use things you did not write. Spring Boot itself. The WebSocket library. The PostgreSQL driver. The JWT library.  
These are called dependencies. Other people wrote them, packaged them, and published them to a central internet registry called Maven Central.  
pom.xml is the file where you list everything your project needs. When you run your project, a tool called Maven reads this file, goes to Maven Central, downloads exactly those libraries at exactly those versions, and makes them available to your code.  
POM stands for Project Object Model. The XML is just the format Maven chose for this file.  
<dependency>  
&nbsp;&nbsp;&nbsp;&nbsp;<groupId>org.springframework.boot</groupId>  
&nbsp;&nbsp;&nbsp;&nbsp;<artifactId>spring-boot-starter-web</artifactId>  
</dependency>  
- groupId is the organization that wrote it. Like a company name.  
- artifactId is the specific library name within that organization.  
- version is which release you want. When you use Spring Boot parent, the version is managed for you so you do not have to specify it on every dependency.  
