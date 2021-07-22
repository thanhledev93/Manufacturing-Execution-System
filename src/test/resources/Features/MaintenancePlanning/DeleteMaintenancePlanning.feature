
Feature: Delete Maintenance Planning System

  Background: Below are common steps for every scenario
    Given User is on "Kế hoạch bảo trì" page with account "hungnq"
    And There is a maintenance plan with status as "Tạo mới"


  Scenario: Delete maintenance planning
    When User choose maintenance planning and click on feature button and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on yes button
    Then Display alert message as "Xóa thành công"
    When User click on confirm button
    Then Close alert message
    And User should not found maintenance planning just deleted in the table
    And close browser

  @UnitTest
  Scenario: Delete failed when canceling create operation
    When User choose maintenance planning and click on feature button and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on no button
    Then Close alert message
    And User should found maintenance planning just deleted in the table
    And close browser
