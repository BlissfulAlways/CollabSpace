# CollabSpace  
  
## TEAM 01 :  
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

### Create a Spring Boot Project :  
Option A: Spring Initializr : A website at start.spring.io  
Option B: Create everything manually : folder structure and dependency file  

pom.xml :  
When you write Java code you will use things you did not write. Spring Boot itself. The WebSocket library. The PostgreSQL driver. The JWT library.  
These are called dependencies. Other people wrote them, packaged them, and published them to a central internet registry called Maven Central.  
pom.xml is the file where you list everything your project needs. When you run your project, a tool called Maven reads this file, goes to Maven Central, downloads exactly those libraries at exactly those versions, and makes them available to your code.  
POM stands for Project Object Model. The XML is just the format Maven chose for this file.  
< dependency >  
&nbsp;&nbsp;&nbsp;&nbsp;< groupId >org.springframework.boot< /groupId >  
&nbsp;&nbsp;&nbsp;&nbsp;< artifactId >spring-boot-starter-web< /artifactId >    
< /dependency >  
- groupId is the organization that wrote it. Like a company name.  
- artifactId is the specific library name within that organization.  
- version is which release you want. When you use Spring Boot parent, the version is managed for you so you do not have to specify it on every dependency.

## TEAM 02 :   
Get a rich text editor rendering in the browser. This means we now move to the frontend folder and start React.  

When a webpage is loaded from one origin, the JavaScript running on that page is not automatically allowed to make requests to a different origin. The browser blocks it before it even leaves the machine.  
An origin is the combination of three things. The protocol, the domain, and the port.  
http://localhost:5173 is one origin.  
http://localhost:8080 is a different origin.  
Same machine. Same domain. But different port. That makes them different origins.  

Browser needs permission from the server at port 8080 before I allow this.  
This permission system is called CORS. Cross Origin Resource Sharing.  
The way it works is this. Before sending your actual request, the browser sends a small preliminary request to port 8080 asking: do you allow requests from port 5173? If the server at port 8080 says yes by including specific headers in its response, the browser allows the request through. If the server says nothing or says no, the browser blocks the request entirely and your frontend gets nothing back.  
We will configure CORS in our Spring Boot backend to explicitly allow requests from port 5173. That configuration goes in our config/ folder which is exactly why that folder exists.  

### Rich Text Editor :  
A textarea is a plain box that holds a string of text. When you type, characters get added to that string. When you delete, characters get removed.  
A textarea has no concept of where a character is beyond its position number. When someone inserts a character at position 3, every character after it shifts by one position -  positions change when anyone types anywhere. (This is a problem)  

Yjs, our conflict resolution library, works with a document that has structure. A document made of nodes. Each node has a unique identity that never changes regardless of what anyone else types. This is what makes CRDT conflict resolution possible.  
So we need an editor that thinks in structured nodes, not raw strings.  

### Tiptap :  
Tiptap is a rich text editor library for React. It is built on something called ProseMirror which is a toolkit for building structured document editors. Tiptap makes ProseMirror usable without understanding all of ProseMirror's complexity. Tiptap has a first class extension for Yjs.  

### React :  
React is a JavaScript library for building user interfaces. In plain HTML you write what the page looks like. In React you write components.  
A component is a JavaScript function that returns what a piece of the page should look like based on data. When the data changes, React automatically updates only the part of the page that changed. You do not manually update the DOM. React handles it.  
Our entire frontend will be built as React components. The editor will be a component. The document list will be a component. The presence indicators will be components.  

### Setting Up React :  
Professional frontend has four qualities :  
- Fast to load — only what the user needs right now gets loaded. Not everything at once.
- Lightweight — no unnecessary libraries. Every dependency has a reason.
- Maintainable — someone who did not write the code can read it and understand it immediately.  Accessible — works for people using keyboards, screen readers, slow connections.

1. Styling — how your app looks :
   - CSS Modules — you write a separate file for each component that contains the visual instructions for that component.
   - Tailwind CSS — instead of writing a separate file, you write the visual instructions directly on the element itself using short class names.
Tailwind produces a smaller final CSS file because it only includes the styles you actually used. CSS Modules includes everything you wrote whether it gets used or not. For a fast loading frontend, smaller CSS matters.

2. State management — how your app remembers things :
Your frontend needs to remember things while it is running. Which user is logged in. Which document is open. Who else is currently online.
These pieces of information are called state.
- Local State : only one component needs it.  React has this built in with something called useState. You do not need an external library for local state.
- Global State : multiple components need the same information.
  1. Context (React built-in) : every component  re-renders when anything in global state changes, even if the change is not relevant to that specific component.
  2. Zustand :  Components subscribe only to the specific pieces of state they need. When user data changes, only components that care about user data re-render. Everything else stays untouched. This directly affects performance.

3. Routing — how your app navigates between pages :
Your frontend has multiple pages. When a user clicks from the dashboard to open a document, the URL changes. The page content changes. But the browser does not reload. React handles the transition entirely in JavaScript.  
The library that manages this URL to component mapping is a router.
- React Router : Standard for years  
- TanStack Router : newer and technically better in specific ways but the community around it is smaller.

4. TanStack Query alongside Zustand. Zustand handles UI state. TanStack Query handles all data fetching from our backend.  
Without it, when your React component needs to load a list of documents from your backend, you would write this logic yourself every single time:  
  
Send the request to the backend  
While waiting, show a loading indicator  
When data arrives, store it somewhere and display it  
If the request fails, show an error message  
If the user navigates away and comes back, decide whether to fetch fresh data or use what you already fetched  
If two components need the same data, decide whether to fetch it twice or share it  
  
That is a significant amount of code you write for every single piece of data your frontend needs from the backend.  
TanStack Query handles all of that automatically. You tell it what to fetch and it manages loading state, error state, caching, and refetching for you.  
The cache means if a user opens the document list, navigates to an editor, then comes back to the document list, the list appears instantly from cache instead of fetching again.  
TanStack Query is only for REST data and not Real time data  (real time WebSocket state managed through Zustand and REST server state managed through TanStack Query)  

### Vite :  
Vite is the build tool.  React code is JavaScript that browsers cannot run directly as you write it. React uses a special syntax called JSX that browsers do not understand. Vite takes your code, transforms it into plain JavaScript that any browser understands, and serves it during development with a fast live reload server.  
When you save a file, Vite detects the change and updates the browser instantly without a full page reload. This is called Hot Module Replacement.  

### Setting Up React Project :  
Project = Folders structure + dependency files  
React Project = React specific folder structure and react specific dependencies  
Command : 'npm create vite@latest . -- --template react'  
- npm : Node Package Manager - maven equivalent, uses package.json
- vite@version = vite@latest : latest version of vite
- . : current folder/directory
- --template react : use react template for react project crestion

Files present :  
- README.md -Auto generated documentation file
- index.html — this is the single HTML file your entire React application lives inside.   

