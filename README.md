# Hair Salon

##### _Java Database Basics Code Review for Epicodus, 28 August 2015_

#### By **Molly Waggett**

## Description

This app allows a hair salon owner to add stylists and clients to a database.
The homepage displays a list of stylists along with a link to add new stylists.
Clicking on a stylist's name takes the user to a list of that stylist's clients.
Also on this page is a link to add a new client, a link to edit (or delete) the
current stylist, and links to edit (or delete) a client by clicking on their name.

_Integration tests that involve fill("#id").with("text") are currently failing,
even though everything seems to be working when the application is run._

## Setup

* Set up the database in PostgreSQL by running the following commands in your terminal:
```
 psql
 CREATE DATABASE hair_salon;
 \c hair_salon;
 CREATE TABLE stylists (id serial PRIMARY KEY, name varchar);
 CREATE TABLE clients (id serial PRIMARY KEY, name varchar, stylist_id int);
```
* If you wish to run tests, create a test database:
```
CREATE DATABASE hair_salon_test WITH TEMPLATE hair_salon;
```
* Clone this repository.
* Using the command line, navigate to the top level of the cloned directory.
* Make sure you have gradle installed. Then run the following command in your terminal:
```
gradle run
```
* Go to localhost:4567.
* Go!

## Technologies Used

* Java
* PostgreSQL
* Spark
* Velocity
* JUnit
* FluentLenium
* Gradle

### Legal

Copyright (c) 2015 **Molly Waggett**

This software is licensed under the MIT license.

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
