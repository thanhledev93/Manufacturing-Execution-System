
Feature: Approval Maintenance Planning
  Background: Below are common steps for every scenario
    Given User is on "Kế hoạch bảo trì" page with account "hungnq"

  Scenario: Submit approval
    Given There is a maintenance plan with status as "Tạo mới"
    When User choose maintenance planning and click on feature button and choose view
    And User click on submit approval button
    And User click on approval process field and choose as "Approval Maintenance Planning - 01"
    Then User should found approval process info in the table
    When User click on submit approval process button
    Then Display alert message as "Lưu thành công"
    When User click on confirm button
    And User click on close button in maintenance planning form
    Then Close view form
    And User should found maintenance planning in the table with status "Chờ duyệt"
    And close browser

  Scenario:  Approval
    Given There is a maintenance plan with status as "Chờ duyệt"
    When User choose maintenance planning and click on feature button and choose view
    And User click on approval button
    And User click on close button in maintenance planning form
    And Close view form
    Then User should found maintenance planning in the table with status "Đã duyệt"
    And close browser

  Scenario:  Returned
    Given There is a maintenance plan with status as "Chờ duyệt"
    When User choose maintenance planning and click on feature button and choose view
    And User click on return button
    And User enter reason as "Reason - test data - Return"
    And User click on return button in return form
    And User click on close button in maintenance planning form
    Then Close view form
    Then User should found maintenance planning in the table with status "Trả về"
    And close browser
