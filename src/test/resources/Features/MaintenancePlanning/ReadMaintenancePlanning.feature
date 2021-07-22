@UnitTest
Feature: Read Maintenance Planning

  Background: Below are common steps for every scenario
    Given User is on "Kế hoạch bảo trì" page with account "hungnq"
    And User has maintenance planning as "KHBT_TEST_DATA"

   Scenario: Read maintenance planning
    When User choose maintenance planning and click on feature button and choose view
    Then Open maintenance planning form
    And Maintenance planning form fields are read-only
    And Maintenance planning form fields are loaded by default
    # VERIFY:
      # 1. Mã bảo trì | Ngày lập | Xưởng | Chuyền | Ghi chú
        | KHBT_TEST_DATA | 19.07.2021 | Sản xuất | Cắt phôi | add new |
      # 2. Table view  hiển thị 4 thiết bị và table lịch chi tiết hiển thị ngày bảo trì của 7 tháng

    When User click on detail button
    Then Open detail calendar form and form fields are loaded by default
    When User click on close button in detail calendar form
    Then Close detail calendar form
    When User click on close button in maintenance planning form
    Then Close maintenance planning form
    And close browser



