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
  
### Spring :  
  
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

### Frontend Basics :  
When you open a browser and visit any website, the browser receives three types of files from a server.  
The first type tells the browser what exists on the page. What elements are there. A heading. A paragraph. A button. An image. This file type is called HTML. HTML stands for HyperText Markup Language. It is not a programming language. It has no logic. It just describes what elements exist.  
The second type tells the browser what those elements look like. The heading is blue. The button has rounded corners. The text is 16 pixels tall. This file type is called CSS. CSS stands for Cascading Style Sheets. It is also not a programming language. It just describes appearance.  
The third type tells the browser what those elements do when the user interacts with them. When this button is clicked, send this data to the server. When this input changes, validate the email format. This file type is called JavaScript. JavaScript is a real programming language. It has logic, conditions, loops, functions.  
Every webpage in existence is built from these three things. HTML for structure. CSS for appearance. JavaScript for behavior.  


#### Why does React exist?  
In the early days of the web, you wrote HTML files directly. One file per page.  
Then JavaScript got powerful enough that instead of the server sending you a new HTML file every time you clicked something, the JavaScript on the page could change the HTML directly without a page reload. This made websites feel like applications.  
But managing this got complicated fast. You have data. That data appears in multiple places on the page. When the data changes, every place that shows it needs to update. Writing that manually in plain JavaScript becomes thousands of lines of code that is nearly impossible to maintain.  
React solves this with one idea. You describe what the page should look like for a given piece of data. React handles updating the page whenever that data changes. You stop thinking about manually updating the page and start thinking only about data and what the page should look like given that data.  

#### What HTML actually looks like :  
HTML is made of elements. Each element has an opening tag, content, and a closing tag.  
Some elements have no content and no closing tag: <img src="photo.jpg" / > : The slash before the closing bracket means this element closes itself. It has no content inside it. Elements can have attributes. src="photo.jpg" is an attribute. A name, an equals sign, and a value in quotes. Attributes give extra information about the element.  
Every HTML page has a required structure:  
< !doctype html >     
< html >  
&nbsp;&nbsp;&nbsp;&nbsp;< head >    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< title >Page Title< /title >   
&nbsp;&nbsp;&nbsp;&nbsp;< /head >  
&nbsp;&nbsp;&nbsp;&nbsp;< body >    
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< h1 >Visible content goes here< /h1 >    
&nbsp;&nbsp;&nbsp;&nbsp;< /body >    
< /html >    
head contains information about the page that is not visible. Title, which CSS files to load, which JavaScript files to load.  
body contains everything the user actually sees  

#### What CSS actually looks like :  
CSS selects elements and applies visual rules to them.  
h1 {
  color: blue;
  font-size: 32px;
}  
h1 is the selector. It means apply these rules to every h1 element on the page. The curly braces contain the rules. Each rule is a property name, a colon, a value, and a semicolon.  
You can also select by class. A class is a name you give to an element:  
< h1 class="title" > Hello < /h1 >  
.title {
  color: blue;
}  
The dot before title means select elements that have this class. This lets you style specific elements instead of all elements of a type.  
  
### Get a rich text editor rendering in the browser. This means we now move to the frontend folder and start React.  

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
< !doctype html >  
< html lang="en" >  
&nbsp;&nbsp;&nbsp;&nbsp;< head >  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< meta charset="UTF-8" / >  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< link rel="icon" type="image/svg+xml" href="/vite.svg" / >  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< meta name="viewport" content="width=device-width, initial-scale=1.0" / >  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< title >frontend< /title >  
&nbsp;&nbsp;&nbsp;&nbsp;< /head >  
&nbsp;&nbsp;&nbsp;&nbsp;< body >  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< div id="root" >< /div >  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< script type="module" src="/src/main.jsx" >< /script >  
&nbsp;&nbsp;&nbsp;&nbsp;< /body >  
< /html >
- < !doctype html > — this tells the browser this is an HTML document. Every HTML file starts with this. It is not a tag. It is a declaration to the browser about what kind of file it is reading.
- < html lang="en" > — the root element of the entire page. Everything visible on the page lives inside this tag. lang="en" tells browsers and screen readers this page is in English.
- < meta charset="UTF-8" > — this tells the browser which character encoding to use when reading this file. UTF-8 is the standard that supports every character in every language. Without this declaration the browser guesses and sometimes guesses wrong, producing garbled text.
- < meta name="viewport" content="width=device-width, initial-scale=1.0" > — this controls how the page scales on mobile devices. Without this line your page would render at desktop width on a phone and the user would have to zoom in to read anything. This single line makes your app mobile friendly by telling the browser to match the screen width of the device.
- < title >frontend< /title > — this is the text that appears in the browser tab.
- < div id="root" >< /div > — this is the most important line in this file. This is an empty div. A div is just a container with no visual appearance of its own. It has one attribute — id="root". This empty div is where React will inject your entire application. When React starts running it finds this div by its id, and replaces it with all your components. The entire CollabSpace interface — the editor, the dashboard, the login page — will live inside this one empty div at runtime.
- < script type="module" src="/src/main.jsx" >< /script > — this tells the browser to load and execute the JavaScript file at /src/main.jsx. This is the trigger. When the browser reads this line it goes and fetches that file and runs it. That file starts React. React finds the div with id root. React injects your application into it. The page comes alive. type="module" means this JavaScript file uses modern ES module syntax — the import and export keywords. Without this attribute the browser would not understand those keywords.

##### One HTML file. One empty div. One script tag. That is the entire foundation of your React frontend.  

Now main.jsx :  
import { StrictMode } from 'react'  
import { createRoot } from 'react-dom/client'  
import './index.css'  
import App from './App.jsx'  
  
createRoot(document.getElementById('root')).render(  
import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(  
&nbsp;&nbsp;&nbsp;&nbsp;< StrictMode >  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;< App / >  
&nbsp;&nbsp;&nbsp;&nbsp;< /StrictMode >,  
)  
- import { StrictMode } from 'react' : import is how JavaScript brings in code from another file or library.
- { StrictMode } — the curly braces mean we are taking one specific thing called StrictMode out of the React library. Not everything React has. Just this one thing.
- from 'react' — this tells JavaScript where to find it. react is not a file you wrote. It is a library that lives inside the node_modules folder that npm downloaded when we created the project.
- StrictMode is a React tool that activates extra checks during development only. It intentionally runs certain parts of your code twice to help you find bugs early. It has zero effect in production. It does not slow your app down for real users. It only runs in development to catch mistakes.
- import { createRoot } from 'react-dom/client' : Taking one specific thing called createRoot from a library called react-dom/client.
- createRoot is the function that connects React to your HTML page. Specifically it connects React to that empty div with id="root"
- import './index.css' : This imports a CSS file. No curly braces because we are not taking a specific named thing. We are just telling the build tool — include this CSS file in the project. Vite sees this import and includes those styles in the final output
- import App from './App.jsx' : This imports your main React component from a file called App.jsx. When a file exports one main thing as its default, you import it without curly braces and you can name it whatever you want. By convention we name it App.
./ means look in the current folder. Not in node_modules. Not somewhere else. Right here.
- document.getElementById('root') — document is the browser's representation of your HTML page. getElementById('root') finds the element with id="root". This finds that empty div we saw in index.html.  
- createRoot(...) — takes that div and tells React: this div is yours. Manage everything inside it.  
- .render(...) — tells React what to put inside that div. Everything inside the render call becomes your visible application.  
< StrictMode >< App / >< /StrictMode > — this looks like HTML but it is not. This is JSX. It is a special syntax that React uses that lets you write what looks like HTML tags inside JavaScript. The browser cannot read this directly — Vite transforms it into plain JavaScript before the browser ever sees it.  
- < App / > means render the App component. Whatever App returns becomes visible on the page.  
- < StrictMode > wraps App to activate those extra development checks   

#### JavaScript : Functions can exist freely. createRoot is just a function. Not inside a class. Just a function.  


now App.jsx - App component :  
It contains Vite's default demo component. It exists just to prove React is working. 
It contains the three most fundamental React concepts you will use in every single component you ever write.  
1. Concept one — a component is just a function :
   - function App() { : A React component is a JavaScript function. It starts with a capital letter by convention — React uses this to distinguish your components from regular HTML tags. Lowercase tags like <div> are HTML. Capitalised tags like <App /> are your components.
   - The function takes no parameters here but components can receive data through parameters called props.  
2. Concept two — state :  
   - const [ count, setCount ] = useState(0) : useState(0) — this is a React function that creates a piece of state. State is data that belongs to this component and can change over time. The 0 is the initial value. Count starts at zero.  
   - useState returns two things. The current value of the state. And a function to change that value.  
   - const [count, setCount] — this is destructuring. Instead of writing:  
&nbsp;&nbsp;&nbsp;&nbsp;const result = useState(0)  
&nbsp;&nbsp;&nbsp;&nbsp;const count = result[0]  
&nbsp;&nbsp;&nbsp;&nbsp;const setCount = result[1]  
   - JavaScript lets you write it in one line by putting variable names inside square brackets matching the positions. count gets the first thing useState returned. setCount gets the second thing  
   - count is the current value. You read it to display it. setCount is the function you call when you want to change it.  
   - The critical rule in React — you never change state directly. You never write count = count + 1. You always call the setter function setCount(count + 1). This is because React needs to know when state changes so it can update the display. If you change state directly React does not know and the display stays wrong.  
3. Concept three — JSX and the return statement :  
   - return (  
&nbsp;&nbsp;&nbsp;&nbsp;<>  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;...  
&nbsp;&nbsp;&nbsp;&nbsp;< / >  
)  
   - Every React component returns what it should display. That return value is written in JSX — the HTML-like syntax inside JavaScript.
   - The empty <> and </> are called a Fragment. React requires every component to return one single root element. But sometimes you want to return multiple elements without wrapping them in a meaningless div. A Fragment is an invisible wrapper. It satisfies React's requirement without adding an extra div to your HTML.  
   - <button onClick={() => setCount((count) => count + 1)}>  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;count is {count}
&nbsp;&nbsp;&nbsp;&nbsp;</ button>
   - Curly braces inside JSX mean — stop treating this as text and evaluate this as JavaScript. {count} means display the current value of the count variable. When count changes, React automatically updates this text.
   - onClick={() => setCount((count) => count + 1)} means when this button is clicked, call this function which calls setCount to increment count by one. React sees the state changed and re-renders the component with the new count value.
   - export default App : This makes the App function available to other files that want to import it. Remember in main.jsx we wrote import App from './App.jsx'. This export default is what makes that import work. Without it, nothing outside this file can use App.
   - npm run dev :  In Codespaces, go to the Ports tab at the bottom of VS Code. You should see port 5173 listed there. Click the globe icon next to it to open it in the browser. Your React app is running and visible in the browser. That is Team 02's first checkpoint done.

#### package.json :  
Before we write a single line of our own code, we need to install the libraries we decided on. Right now your package.json only knows about React and Vite. It knows nothing about Tailwind, Zustand, TanStack Query, React Router, Tiptap, or Yjs. 

npm install @tiptap/react@2 @tiptap/pm@2 @tiptap/starter-kit@2 @tiptap/extension-collaboration@2 @tiptap/extension-collaboration-cursor@2 yjs y-websocket zustand @tanstack/react-query @tanstack/react-query-devtools react-router-dom  

  - npm install — this tells npm to download libraries from the JavaScript registry and add them to your project. The equivalent of what Maven does when it reads your pom.xml and downloads dependencies. Except here you are naming the libraries directly in the command instead of editing a file first. npm will automatically add them to your package.json after downloading.  
  - Everything after npm install is just the list of libraries you want. Each one separated by a space.  
  - @tiptap/react — the core Tiptap editor library built for React. This is the editor itself. Without this there is no rich text editing capability. 
  - @tiptap/pm — ProseMirror is the underlying document model that Tiptap is built on. Tiptap needs this to function. You will never use this directly but Tiptap depends on it internally.  
  - @tiptap/starter-kit — a bundle of the most common editor features. Bold text, italic text, headings, bullet lists, paragraph formatting. Instead of installing each feature separately this one package gives you all the basics.  
  - @tiptap/extension-collaboration — this is the specific Tiptap extension that connects the editor to Yjs. Without this, Tiptap and Yjs do not know about each other. This extension makes Tiptap use Yjs as its document store instead of its own internal store.  
  - @tiptap/extension-collaboration-cursor — this adds the cursor presence feature. When another user is in the document, their cursor appears with their name and a color. This extension handles rendering those cursors inside the editor.  
  - yjs — the core CRDT library. This is the mathematical foundation of our conflict resolution. It manages the shared document state and guarantees convergence.  
  - y-websocket — Yjs by itself manages document state but does not know how to send changes over a network. This library gives Yjs a WebSocket provider. It connects the Yjs document to a WebSocket connection so changes travel between browsers automatically.  
  - zustand — our global state management library. Manages things like which user is logged in, connection status, and other UI state that multiple components need to share.  
  - @tanstack/react-query — manages all data fetching from our backend REST API. Handles loading states, error states, and caching automatically.  
  - @tanstack/react-query-devtools — a development tool that shows you what TanStack Query is doing internally. Which requests are in flight, what is cached, when data was last fetched. Only used during development, has zero impact on production.  
  - react-router-dom — handles navigation between pages. Login page, dashboard page, editor page. When the URL changes React Router decides which component to show.  
  - some libraries start with @tiptap/ and some with @tanstack/. The @ symbol followed by a name before the slash is called a scope. It is a way of grouping related packages under one organization name on npm.  
  - @tiptap/react and @tiptap/pm are both published by the Tiptap organization. @tanstack/react-query and @tanstack/react-query-devtools are both published by the TanStack organization.  
  - This prevents naming collisions. If two different organizations both made a library called react-query, they would conflict. Scopes solve this by namespacing them.  
  
Install Tailwind CSS : npm install -D tailwindcss @tailwindcss/vite  
- The -D flag means install as a development dependency. Tailwind is a build time tool. It reads your JSX files, finds every Tailwind class you used, and generates only those styles into a CSS file. After that build step Tailwind itself is no longer needed. The generated CSS file is what ships to users. So Tailwind belongs in development dependencies, not production dependencies.
- This distinction appears in your package.json as two separate sections. dependencies are needed at runtime. devDependencies are only needed during development and building.

Inside package.json :  
- "type": "module" — this tells Node.js that every JavaScript file in this project uses modern ES module syntax. Meaning import and export keywords. Without this line Node.js would expect the older require() syntax. This one line is why you can write import React from 'react' instead of const React = require('react').
- "scripts" — these are shortcuts for terminal commands. When you type npm run dev, npm looks up dev in this scripts section and runs vite. You are not typing vite directly because vite is installed inside node_modules and your terminal does not know where that is. npm knows and handles it for you.
- dev — starts the development server with hot reload
- build — compiles everything into plain JavaScript for production
- lint — checks your code for errors and bad practices
- preview — serves the production build locally so you can test it before deploying
- The ^ symbol before every version number — this means accept this version or any newer version that does not have breaking changes. ^5.90.21 means version 5.90.21 or anything newer in the 5.x.x range. It will never automatically upgrade to version 6 because that could have breaking changes. This gives you automatic bug fixes and minor improvements without risking your code breaking.  
  
Tailwind needs two small configuration steps before it works :  
Step one — tell Vite to use Tailwind - vite.config.js  
  - This file is a JavaScript file that exports one object. That object contains configuration instructions for Vite.
  - In JavaScript, export default means this file's main output is what follows. Any file that imports this file gets this object.
  - defineConfig({}) is just a function call. You are passing one object inside it. That object has one property right now called plugins. The value of plugins is an array — the square brackets [] mean array in JavaScript. Inside that array sits one item — react() which is a function call that returns the React plugin.  
javascript  
import { defineConfig } from 'vite'  
import react from '@vitejs/plugin-react'  
import tailwindcss from '@tailwindcss/vite'  
  
// https://vite.dev/config/  
export default defineConfig({    
&nbsp;&nbsp;&nbsp;&nbsp;plugins: [  
&nbsp;&nbsp;&nbsp;&nbsp;react()  
&nbsp;&nbsp;&nbsp;&nbsp;tailwindcss(),  
],  
})  
  
- Right now it has one plugin — the React plugin that enables JSX transformation.  
- We need to add the Tailwind plugin so Vite processes Tailwind classes during the build. (done above)  
  
Step two — tell Tailwind to activate in your CSS  
src/index.css :  




