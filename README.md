# QuizApp

QuizApp is an application that allows users to create quizzes made up of multiple questions and responses. Each question has a difficulty level, a topic, a content, and a list of responses. Each response has a text and a boolean value that indicates whether it is correct or not.
## How to install this project

In project folder there is folder called `Relevant Folder` which contains all files used within this project.

_*If you are using Intellij IDEA*_: open project in Intellij IDEA, project contains ".idea" folder with all run configurations set up. You will see there 2 run configurations required within this project. MySQL driver is also preinstalled, as well as environment variables. You only need to _**execute MySQL command**_ from folder described above on you DB.

_*If you are not using Intellij IDEA*_: You have to perform following steps:

1) Execute MySQL command from `Relevant Folder` directory
2) Link this project to MySQL driver from `Relevant Folder` directory
3) Set up given in `.env` environment variables for both `Main` Class and `DaoQuestionTest` Class.
4) Run project by using either `Main` Class or `DaoQuestionTest` Class