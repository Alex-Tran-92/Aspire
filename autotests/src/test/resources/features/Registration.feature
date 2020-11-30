Feature: Registration flow

  @aspire_auto
  Scenario: Registration with role in company is director
    Given the user is on the login page
    When the user select register link
    And the user fill the info to register as below
      | roleInCompany       | registerPersonHeardAbout | promoCode |
      | Appointed director  | Others                   | None      |
    And the user stick the privacy checkbox
    And the user click the "Continue" button
    And the user fill the phone OTP
    And the user click the "Continue" button
    When the user choose "Continue" button with registered business in Singapore with ACRA
    And the user choose get started with "Standard Registration"
    And the user click the "Get Started" button
    And the user select date of birth as below
      | year | month | day |
      | 1991 | Nov   | 21  |
    And the user select "Nationality" is "American Samoa"
    And the user select "Gender" is "Female"
    And the user select "Which products are you interested in?" is "Others"
    And the user click the "Submit" button
    And the user fill the phone OTP
    When the user click the "Get Started" button
    And the user fill the info for business detail as below
      | businessName | registrationType                       | industry          | subIndustry                                        |
      | Aspire       | Company (Public company, Pte Ltd, LLC) | Business Services | Banking, Investments, Lending & Financial Services |
    And the user click the "Submit" button


