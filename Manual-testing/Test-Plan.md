# Manual Test Plan - Para Bank: Input Validation

## Introduction
This document defines the manual test plan to validate the **input constraints** for key user forms and actions in Para Bank. Each form's fields have defined input rules that must be strictly enforced to ensure security, consistency, and data integrity.

---

## Test Types

| Test Type         | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| Input Validation  | Verifies that input fields enforce rules like length, format, and type.     |
| Functional Testing| Ensures user can perform actions after entering valid inputs.               |
| Boundary Testing  | Checks input limits (min/max characters, numeric ranges, etc.).             |
| Negative Testing  | Enters invalid values to verify that appropriate error messages appear.     |

---

## Test Cases by Section

### 1. Login Page

| TC ID | Field     | Test Scenario                    | Test Input             | Expected Result                                |
|-------|-----------|----------------------------------|------------------------|------------------------------------------------|
| TC01  | Username  | Empty input                      | (blank)                | Error: "Username is required"                  |
| TC02  | Username  | Too short                        | "abc"                  | Error: "Minimum 5 characters"                  |
| TC03  | Username  | Contains symbols                 | "user!@#"              | Error: "Only alphanumeric characters allowed"  |
| TC04  | Password  | Empty input                      | (blank)                | Error: "Password is required"                  |
| TC05  | Password  | Too short                        | "abc123"               | Error: "Minimum 8 characters"                  |
| TC06  | Valid login | Valid username and password     | "user123" / "pass123!" | Successful login, redirect to dashboard        |

---

### 2. Registration Form

| TC ID | Field         | Test Scenario                    | Test Input                      | Expected Result                                   |
|-------|---------------|----------------------------------|----------------------------------|--------------------------------------------------|
| TC07  | First Name    | Numbers in name                  | "John123"                        | Error: "Only alphabetic characters allowed"      |
| TC08  | Address       | Exceeds 100 chars                | (101 characters)                | Error: "Maximum 100 characters"                  |
| TC09  | Zip Code      | Letters instead of digits        | "ABCDE"                          | Error: "Only numeric values allowed"             |
| TC10  | Phone         | Too short                        | "12345"                          | Error: "Minimum 10 digits required"              |
| TC11  | SSN           | Too long                         | "1234567890"                     | Error: "Must be 9 digits"                        |
| TC12  | Username      | Already taken                    | "existingUser"                   | Error: "Username already in use"                 |
| TC13  | Password      | Password mismatch                | "Pass1234" / "Pass4321"          | Error: "Passwords do not match"                 |
| TC14  | All valid     | All valid inputs                 | Proper values                   | Account successfully created                     |

---

### 3. Transfer Funds

| TC ID | Field         | Test Scenario                    | Test Input                   | Expected Result                                |
|-------|---------------|----------------------------------|------------------------------|-------------------------------------------------|
| TC15  | Amount        | Empty                            | (blank)                      | Error: "Amount is required"                    |
| TC16  | Amount        | Zero                             | "0"                          | Error: "Amount must be greater than 0"         |
| TC17  | Amount        | Letters                          | "ten dollars"                | Error: "Numeric values only"                   |
| TC18  | From/To       | Same account selected            | Account A → Account A        | Error: "Accounts must be different"            |
| TC19  | Valid transfer| Correct amount and accounts      | $100, A → B                  | Successful transfer                            |

---

### 4. Open New Account

| TC ID | Field         | Test Scenario                    | Test Input                  | Expected Result                                |
|-------|---------------|----------------------------------|-----------------------------|-------------------------------------------------|
| TC20  | Account Type  | Not selected                     | (none)                      | Error: "Please select an account type"         |
| TC21  | Existing Acct | Not selected                     | (none)                      | Error: "Please choose an existing account"     |
| TC22  | Valid         | Checking + Valid existing account| "Checking" + Account #1234  | New account created successfully               |

---

## Success Criteria

- All validation rules are enforced accurately.
- Invalid inputs always trigger clear, specific error messages.
- Valid inputs proceed to the next logical step (login, account creation, etc.).
- No critical issues found during input-related testing.

---

## Notes

- Test across multiple browsers (Chrome, Firefox, Edge).
- Ensure consistency of validation both on frontend (JS) and backend (API).
- Screenshots of failed cases should be logged with bug reports.
Test Objectives
The main objectives of this test plan are:

 To verify that all form fields in Para Bank enforce input constraints correctly.

 To ensure valid user interactions lead to expected outcomes (e.g., successful login, account creation).

 To identify and report any bugs, validation issues, or usability concerns before deployment.

 To confirm that error messages are clear, actionable, and match the expected validation rules.

 To ensure proper handling of edge cases (e.g., max/min inputs, invalid characters, missing values).

Test Scope
Included in Scope:

Login functionality (input validation and successful/unsuccessful login).

Registration form input constraints (e.g., required fields, format, password rules).

Transfer Funds logic (e.g., positive amounts, different accounts).

Open New Account constraints (account type selection, account linkage).

UI validation for input error prompts and form layout across devices.

Basic security testing related to input handling (e.g., injection, script tags).

Excluded from Scope:

Backend data persistence and database-level testing.

Advanced security testing (e.g., penetration testing).

Performance/load testing.

Automated testing (this plan is manual only).

Test Tools
Since this is a manual testing plan, tools will focus on documentation, tracking, and test support:

Tool	Purpose
Browser DevTools	Inspecting elements, console errors
Excel / Google Sheets	Logging test results and defects
Screenshot tool	Capturing evidence of issues
Jira 	Issue tracking and test progress
