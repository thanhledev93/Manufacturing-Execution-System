@UnitTest
Feature: Search Maintenance Planning

  Background: Below are common steps for every scenario
    Given User is on "Kế hoạch bảo trì" page with account "hungnq"


  Scenario: User found MP in the table when choosing factory and factory line and approval status valid
    When User click on factory field and choose factory as "Sản xuất"
    And User click on factory line field and choose factory line as "Cắt phôi"
    And User click on approval status field and choose status as "Đã duyệt"
    Then User should found maintenance planning in the table when searching
    And close browser


  Scenario: User not found MP in the table when enter planning number inValid
    When User click on factory field and choose factory as "Sản xuất"
    And User click on factory line field and choose factory line as "Cắt phôi"
    And User click on approval status field and choose status as "Tất cả"
    And User click on Search field and enter planning number as "inValue"
    And User click on search button
    Then User should not found maintenance planning in the table when searching
    And close browser

  Scenario:  User found MP in the table with combination of criteria
    When User click on factory field and choose factory as "Sản xuất"
    And User click on factory line field and choose factory line as "Cắt phôi"
    And User click on approval status field and choose status as "Tất cả"
    And User click on Search field and enter planning number as "KHBT_TEST_DATA"
    And User click on search button
    Then User should found maintenance planning in the table when searching
    And close browser


#  - Tìm kiếm theo giá trị mặc định (không nhập tiêu chí tìm kiếm nào)
#  - Tìm kiếm không ra kết quả
#  - Tìm kiếm theo từng tiêu chí đơn lẻ
#  - Tìm kiếm kết hợp các tiêu chí



