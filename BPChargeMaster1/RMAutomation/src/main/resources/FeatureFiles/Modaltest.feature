Feature: Modal Test
@Modal
Scenario: When confirmation on Preference

  Given That user has already login
  When Have navigated to the my Details and Preference Tab
  Then I can see user selections on Tabs

