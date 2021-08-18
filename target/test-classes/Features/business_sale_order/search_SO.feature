
Feature: Search Business Sale Order

  Background: Below are common steps for every scenario
    Given User is on "Đơn hàng bán" page with account "administrator"

  @Integration
  Scenario: SO should appear in the SO list when searching with valid value
    When User search a SO with customer and status
      | DAE WON CHONG UP CORPORATION        | Đã duyệt        |
    Then SO should appear in the SO list
    And close browser

  Scenario: SO should appear in the SO list when searching with valid value
    When User search a SO with customer and status
      | DAE WON CHONG UP CORPORATION        | Đã duyệt        |
    Then SO should appear in the SO list
    And close browser

  Scenario: SO not appear in the SO list when searching with invalid value
    When User search a SO with SO number "invalid"
    Then SO should not appear in the SO list
    And close browser


#  - Tìm kiếm theo giá trị mặc định (không nhập tiêu chí tìm kiếm nào)
#  - Tìm kiếm không ra kết quả
#  - Tìm kiếm theo từng tiêu chí đơn lẻ
#  - Tìm kiếm kết hợp các tiêu chí



