# Aspire

### 1. The test automation framework is comprised of following tools and libraries
- Cucumber-JVM + TestNG: BDD Framework
- Step definitions with lambda expressions (cucumber-java8)
- Page Object Pattern
- Selenium: Browser automation tool
- JAVA: Programming language
- Maven: Build and dependencies tool
- Intellij: Integrated Development Environment
- AssertJ: Matcher's

### 2. How to run Test
- Update browser want to test to versions latest before run test
- Use IntelliJ of JetBrains (Community): https://www.jetbrains.com/idea/download/#section=windows
- Install Cucumber for Groovy
- run all tests: mvn clean verify
- run by tag : mvn -D cucumber.options='--tags @abc' verify
- run test using specified browser: mvn -Dbrowser=firefox verify (default is Chrome)
- run tests in parallel and generate report: mvn clean verify -Ddataproviderthreadcount=3