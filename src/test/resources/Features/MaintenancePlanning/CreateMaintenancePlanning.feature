@UnitTest
Feature: Create Maintenance Planning System

  Background: Below are common steps for every scenario
    Given User is on "Kế hoạch bảo trì" page with account "hungnq"


  Scenario: Create maintenance planning with valid Value.
    When User click on create maintenance planning button
    Then Open maintenance planning form
    When User enter maintenance planning number
    And User click on factory field and choose factory as "Sản xuất" in maintenance planning form
    And User click on factory line field and choose factory line as "Cắt phôi" in maintenance planning form
    And User enter note
    And User change created date
    And User change expected date of devices
    And User click on create calendar button
    Then Display alert message as "Tạo lịch thành công"
    When User click on confirm button
    Then Close alert message

    When User click on detail button
    Then Open detail calendar form and form fields are loaded by default
    When User click on close button in detail calendar form
    Then Close detail calendar form

    When User click on save button in maintenance planning form
    Then Display alert message as "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And User should found maintenance planning in the table
    And close browser

  Scenario: Create maintenance planning with required values is blank
    When User click on create maintenance planning button
    Then Open maintenance planning form
    When User not enter plan number
    And User enter note
    And User click on save button in maintenance planning form
    Then Display alert message as "Bắt buộc" for required values in maintenance planning form
    And close browser

  Scenario: Create maintenance planning with plan number already exist.
    Given There is a maintenance plan with plan number as "KHBT_TEST_DATA"
    When User click on create maintenance planning button
    Then Open maintenance planning form
    When User enter maintenance planning number with plan number as "KHBT_TEST_DATA"
    And User click on factory field and choose factory as "Sản xuất" in maintenance planning form
    And User click on factory line field and choose factory line as "Cắt phôi" in maintenance planning form
    And User enter note
    And User change expected date of devices
    And User click on create calendar button
    Then Display alert message as "Tạo lịch thành công"
    When User click on confirm button
    Then Close alert message
    And User click on save button in maintenance planning form
    Then Display alert message as "Đã tồn tại" for required values in maintenance planning form
    And close browser


  Scenario: Create failed when canceling create operation
    When User click on create maintenance planning button
    Then Open maintenance planning form
    When User enter maintenance planning number
    And User click on factory field and choose factory as "Sản xuất" in maintenance planning form
    And User click on factory line field and choose factory line as "Cắt phôi" in maintenance planning form
    And User enter note
    And User change created date
    And User change expected date of devices
    And User click on create calendar button
    Then Display alert message as "Tạo lịch thành công"
    When User click on confirm button
    Then Close alert message
    When User click on close button in maintenance planning form
    And User should not found maintenance planning in the table
    And close browser




#  Scenario: Create system with System name is special characters

#  Kiểm tra hiển thị confirm trước khi thêm
#  Kiểm tra hủy bỏ thao tác thêm mới
#  Kiểm tra xác nhận thêm mới
#  Kiểm tra lưu vào DB
#  Kiểm tra check trùng,...
#  Kiểm tra chỉ nhập các trường bắt buộc
#  Kiểm tra nhập tất cả các trường đạt maxlength
#  Kiểm tra khi nhấn nút thêm mới liên tục


