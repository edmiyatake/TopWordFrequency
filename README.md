# TopWordFrequency Program

## Overview

The WordFrequency program is a Java application that:
- Reads a text file containing the content to analyze.
- Loads a stopwords file and removes any stop words from the analysis.
- Counts the frequency of each non-stop word in the text.
- Displays the top **N** most frequent words (default is 25).

## Project Folder Structure

Place the following files in the folder:

WordFrequency.java // the main file
test.txt // Your input text file for analysis.
stopwords.txt // File containing stopwords (comma-separated or one per line).

## How to Compile and Run

1. **Open Command Prompt / Terminal**

2. **Navigate to the Source Folder**

   Change directory to where the Java file and the text files are located:

   cd path\untitled\src
3. Compile the Program

Run the following command to compile the Java program: javac WordFrequency.java

4. Run the Program

java WordFrequency test.txt stopwords.txt 25

5. Confirm the expected results with the example in the textbook
6. Now rerun the program for pride prejudice

java WordFrequency test.txt stopwords.txt 25
