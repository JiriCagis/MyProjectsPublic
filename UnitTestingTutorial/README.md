<h1>Unit testing tutorial</h1>
<div>
Goal this project is show usage unit tests on a base data structure Stack, a generator primes numbers by Sieve algorithm
and a mapping object from/to database. Unit tests are decide for verify part of source code. In our case we use unit tests for verify methods in classes. Unit tests are a worth tool programmers when they found errors or changed existing code. Right wrote unit tests save more time in later period, after program contains thousands lines. Next advance of unit testing is automatic run it. All tests in this tutorial are wrote in programming language Java with tool JUnit 4.0.
</div>
<h3>Stack</h3>
<div>
   Stack is used as a dynamic data structure for save data. Structure has a rule that item inserted last is take away as first. Main functions are
   <b>push()</b> put the item on top, <b>pop()</b> get the item from top and remove it, <b>top()</b> get the item on top without 
   remove it and <b>isEmpty()</b> verify whether stack contain any items.
</div>
<br>
<b>Example tests:</b>
<ul>
   <li>Create a Stack and verify that isEmpty is true</li>
   <li>Push a single object on the Stack and verify that IsEmpty is false</li>
   <li>Push a single object, Pop the object and verify that isEmpty is true</li>
   <li>Pop a Stack that has no elements</li>
   <li>Push a single object, remembering what they are: Pop each one, and verify that they are removed in the correct order.</li>
</ul>
<h3>Prime numbers</h3>
<div>
   Second part of project is focuses on refactoring code mathematic library contains a method generate primes.
   For generate primes is use the algorithm Sieve of Eratothemes. To better understanding how work algorithm. Primes
   are numbers divisible only number one and itself, but a number must be greater than the number one. Change code was need,
   because into library was add method for get primes return instead array numbers list numbers and previous method is now deprecated. Moreover code the algorithm was write in procedual style and illegibly.
</div>
<br>
<b>Refactoring</b>
<ol>
   <li>Hide method a generateArray() set permission private.</li>
   <li>Copy code from generateArray to method generate and remove method generateArray.</li>
   <li>Rename variable (f --> isPrime).</li>
   <li>Collapse loops for place result to list.</li>
   <li>Remove dead code, variable count primes not need, because we new use dynamic data structure list.</li>
   <li>Collapse loop again (extend boundary algorithm from "Math.sqrt(s) + 1"  to "s" and join with add item to result.</li>
   <li>Reduce local variable scope.</li>
   <li>Replace temp variable "s" with query.</li>
   <li>Remove code rid know primes for 0 and 1, because algorithm not access to them.</li>
   <li>Simplify init isPrime with use build method Array.fill(). </li>
</ol>
<h3>Music library</h3>
<div>
Last part of project contains mapping database tables into object in programming language. For access to tables
was used a design pattern DAO (Data access object) that each class represent one table and classes methods 
oparations on table. In the DAO is used new approach by Stream API published in Java 8, bacause Stream can use
advantage multi-core processors.
</div>


<p align='center'>
<img src="https://github.com/JiriCagis/UnitTestingTutorial/blob/master/databaseSchema.png" width="90%"/>
</p>

<br>
<b>Requiremens</b>
<ol>
<li> Installed database. I used in project H2 base, but it should be work with others as Oracle, mySQL or PostgreSQL.
<a href="http://www.h2database.com/html/installation.html"> How to install H2 base</a></li>
<li> Loaded table schema to database, it place in file
<a href="https://github.com/JiriCagis/UnitTestingTutorial/blob/master/src/main/resources/initDatabase.sql"> initDatabase.sql </a></li>
<li> Verifed right login credentials in file 
<a href="https://github.com/JiriCagis/UnitTestingTutorial/blob/master/src/main/resources/database.properties"> database.properties</a></li>
</ol>
<div>
<b> Example tests: </b> <br>
The tests are divided to three parts, first group creates tests for get a connection string and verify the connection to database.
Second part contains tests verify individual tables in isolation as insert, update and delete record. Last serie tests is aim on test relationships between tables.
<br><br>
<ul>
<li>Connection to the database</li>
<li>Individual tables in isolation (Recording, Label, Review, Reviewer, Track, Artist, and Genre) </li>
<li>Choose relationship between tables (Recording-Reviews, Recording-Tracks, Review-Reviewer, Track-Artist, Track-Genre)
</ul>
</div>

