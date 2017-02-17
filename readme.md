************** Automation Test Analyst – ABC assignment ***************

This assignment is divided into 3 sections: Section A, B and C

Section A:
----------
To run all the test cases of this section, simply run the program "SectionATests.java" - It contains 6 Test Cases # 1-6

This section is also designed as to run with page wise:
- To run test cases of page News, run the script "NewsTests.java" - It contains Test Cases # 1, 2, 3, 5, 6
- To run test cases of page Just In, run the script "JustInTests.java" - It contains Test Case # 4 and an Extra test case to verify the page load with URL


Section B:
----------
To run all the test cases of this section, simply run the program "SectionBTests.java" - It contains 7 Test Cases # 1-7

This section is also designed as to run with page wise. 
- Unlink previous section, here all the test cases are added in a page RadioNationalTests.Java to simplify the exercise. 


Section C:
----------
To run all the test cases of this section, simply run the program "SectionCTests.java" - It contains 3 Test Cases with 2 Extra test case

Test case 1: Loads the JSON url and verify the key/value pairs 
Test case 2: Tests the JSON program against 2 environments (Test and Stagging) - The test will fail because the environment doesn't exist
Test case 3: Tests multiple JSON program against live environment
Test case 4: Tests multiple programs on multiple environments
Test case 5: Loads the JSON file located in <Working Directory>\src\jsonfiles\

Above test cases use a function ReadJsonWithJackson with 3 parameters
- First parameter is the JSON URL
- Second parameter is the JSON file path
- Third parameter is to decide if use the URL or file path - if the value is true then it uses the filepath otherwise URL

If you want to add a new environment then add a String value in Environment.java file (e.g. public static final String local = "local-program";)

If you want to add a new program then add a String value in Programs.java file (e.g. public static final String evening = "ppJj0E8g2R";)

You can use <ClassName>.<String> to select the program or enviornment (e.g. Programs.morning, Environment.stagging)




