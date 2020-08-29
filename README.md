# File Validation
ABC Bank Customer Statement Validator

ABC Bank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.

# Input
| Field | Descrption |
| ------ | ------ |
|Transaction reference | A numeric value |
|Account number | An IBAN |
|Start Balance | The starting balance in Euros |
|Mutation | Either an addition (+) or a deduction (-) |
|Description | Free text | 
|End Balance |The end balance in Euros |

# Output
There are two validations:
  - all transaction references should be unique. Display message duplication occurances and Account number
  - the end balance needs to be validated. It will consider invalid if the End Balance is negative or not matching with the Start Balance + Mutation

# Technologies
  - SpringBoot
  
# Prerequisite!
  - JDK 1.8

# Installation
- Download executable jar FileValidation-0.0.1-SNAPSHOT.jar file from the executable folder.
- Execute below commands in the command prompt
    ```cmd  
      cd <TARGET_FOLDER>
      java -jar FileValidation-1.0.RELEASE.jar
    ```