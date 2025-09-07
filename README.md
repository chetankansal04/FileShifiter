

<h1>FileShifter: Your Effortless File Converter</h1>

<h2>Project Description</h2>
<p>FileShifter is a full-stack web application designed for secure and seamless file conversions. Users can register, log in, and convert various file types with a few simple clicks. The application is built on a modern, decoupled architecture with a robust backend and a dynamic frontend.</p>

<h2>Features ✨</h2>
<ul>
<li><b>Secure Authentication</b>: User registration and login powered by Spring Security and JWTs.</li>
<li><b>Cloud-Based File Storage</b>: All uploaded and converted files are stored securely in an <b>AWS S3</b> bucket.</li>
<li><b>Efficient Conversion Pipeline</b>: A decoupled backend architecture allows for scalable file processing.</li>
<li><b>Intuitive User Interface</b>: A modern, responsive frontend built with <b>Next.js</b>.</li>
<li><b>Database Management</b>: Conversion jobs and user data are managed in a <b>PostgreSQL</b> database.</li>
</ul>

<h2>Technologies Used 🛠️</h2>
<h4>Backend</h4>
<ul>
<li><b>Spring Boot</b>: The core framework for building the backend.</li>
<li><b>Spring Security</b>: For handling authentication and protecting API endpoints.</li>
<li><b>Spring Data JPA</b>: For data persistence and interacting with the database.</li>
<li><b>PostgreSQL</b>: The relational database for storing user and job metadata.</li>
<li><b>AWS SDK for Java</b>: For programmatic interaction with AWS S3.</li>
<li><b>JJWT</b>: A library for creating and validating JSON Web Tokens.</li>
<li><b>BCrypt</b>: A secure algorithm for hashing user passwords.</li>
</ul>

<h4>Frontend</h4>
<ul>
<li><b>Next.js</b>: The React framework for building the user interface.</li>
<li><b>React</b>: The core library for building UI components.</li>
<li><b>Tailwind CSS</b>: A utility-first CSS framework for fast styling.</li>
</ul>

<hr>

<h2>Getting Started</h2>
<p>Follow these steps to get a local copy of the project up and running.</p>

<h3>Prerequisites</h3>
<ul>
<li>Java Development Kit (JDK) 17 or higher</li>
<li>Node.js and npm/yarn</li>
<li>A PostgreSQL database (e.g., a free instance from Neon)</li>
<li>An AWS S3 bucket and IAM credentials</li>
</ul>

<h3>1. Clone the Repository</h3>
<pre><code>git clone https://github.com/your-username/your-repository.git
cd your-repository
</code></pre>

<h3>2. Backend Setup</h3>
<p>Navigate to the <code>file-converter-backend</code> directory. Create an <code>application.properties</code> file in <code>src/main/resources</code> with your database and AWS credentials.</p>
<pre><code>spring.datasource.url=jdbc:postgresql://&lt;host&gt;:5432/&lt;database&gt;?sslmode=require
spring.datasource.username=&lt;user&gt;
spring.datasource.password=&lt;password&gt;
spring.jpa.hibernate.ddl-auto=update

aws.s3.bucketName=&lt;your-s3-bucket-name&gt;
aws.accessKeyId=&lt;your-aws-access-key-id&gt;
aws.secretKey=&lt;your-aws-secret-key&gt;
</code></pre>
<p>Build and run the application.</p>
<pre><code>./mvnw spring-boot:run
</code></pre>

<h3>3. Frontend Setup</h3>
<p>Navigate to the <code>file-converter-frontend</code> directory. Install dependencies.</p>
<pre><code>npm install
</code></pre>
<p>Start the Next.js development server.</p>
<pre><code>npm run dev
</code></pre>
<p>The application will be available at <code>http://localhost:3000</code>.</p>
