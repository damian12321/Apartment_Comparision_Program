## Table of contents
* [General info](#general-info)
* [Inspiration](#inspiration)
* [Program's idea](#program's-idea)
* [Technologies](#technologies)
* [Screenshots](#screenshots)
* [Setup](#setup)

## General info
This project is a simple apartment comparision program.
	
## Inspiration
The program was created for my own needs when I was looking for an apartment in a specific housing estate in Krakow.

## Program's idea
The application has two modes of operation.

* Compare the data from one .txt file with the data in second .txt file. 
* Compare the data from .txt file with the online values ​​in the apartments table.

The program includes a simple graphical user interface.
The result of the program is a .txt file that contains information about any changes in individual apartments, such as price, availability and surface.

## Technologies
Project is created with:
* Java 8
* Maven
* JUnit 5

## Screenshots
![Screen1](./img/screen1.png)
![Screen2](./img/screen2.png)

## Setup
Clone the repo from github:

git clone https://github.com/damian12321/Apartment_Comparision_Program

You can run the application on your favourite IDE by running src/main/java/pl/apartment_comparator/Main.main
or by command line in the application root folder.

```
$ mvn clean compile assembly:single
$ java -jar target/ApartmentComparator-1.0.0-jar-with-dependencies.jar
```

An example files that can be used to test the program are located in src/main/resources.
An example result file can be found in src/main/resources/differences.txt.
