Feature: Registration

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
    And the user choose "Continue" button with registered business in Singapore with ACRA
    And the user choose get started with "Standard Registration"
    And the user click the "Get Started" button
    When the user select date of birth as below
      | year | month    | day |
      | 1991 | November | 21  |

