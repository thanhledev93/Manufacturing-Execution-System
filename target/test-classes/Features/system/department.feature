#noinspection NonAsciiCharacters

@UAT_Testing
Feature: Department

  Background:
    Given User is on "Phòng ban" page with account "admin" and password "123456@#"

#  CREATE DEPARTMENT ******************************************
#  @create @update  @delete  @revert @search @read
  @jenkins
  Scenario Outline: Create department successful with valid value
    When User click on create department button
    Then Open department form
    When User enter department name "<name>" and note "<note>"
    And User click on save button in department form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And User should found department with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name        | note             |
      | Phòng ban 1 | phòng ban 1 note |
      | Phòng ban 2 | phòng ban 2 note |
      | Phòng ban 3 | phòng ban 3 note |

#  @create
  @jenkins
  Scenario: Create department failed with name is blank
    When User click on create department button
    Then Open department form
    When User enter note "phòng ban note"
    And User click on save button in department form
    Then Display alert message "Bắt buộc" for required values in department form
    And Close browser

#  @create
  @jenkins
  Scenario Outline: Create department failed with name already exist
    When User click on create department button
    Then Open department form
    When User enter department name "<name>" and note "<note>"
    And User click on save button in department form
    Then Display alert message "Đã tồn tại" for required values in department form
    And Close browser
    Examples:
      | name        | note             |
      | Phòng ban 1 | phòng ban 1 note |

#  @create
  @jenkins
  Scenario Outline: Create department failed when canceling create operation
    When User click on create department button
    Then Open department form
    When User enter department name "<name>" and note "<note>"
    When User click on close button in department form
    Then Close department form
    And User should not found department with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name        | note             |
      | Phòng ban 4 | phòng ban 4 note |


# READ DEPARTMENT ******************************************
#  @read
  Scenario Outline: Read department
    When User choose department "<name>" and choose view
    Then Open department form
    And Department form fields are read-only
    And Department form fields "<name>" and "<note>" are loaded by default
    When User click on close button in department form
    Then Close department form
    And Close browser

    Examples:
      | name        | note             |
      | Phòng ban 1 | phòng ban 1 note |

# DELETE DEPARTMENT
#  @delete @revert
  Scenario Outline: Delete department
    When When user choose department "<name>" and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on yes button
    Then Display alert message as "Xóa thành công"
    When User click on confirm button
    Then Close alert message
    And User should not found department with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name        | note             |
      | Phòng ban 1 | phòng ban 1 note |

#  @delete
  Scenario Outline: Delete failed when canceling create operation
    When When user choose department "<name>" and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on no button
    Then Close alert message
    And User should found department with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name        | note             |
      | Phòng ban 2 | phòng ban 2 note |

# REVERT DEPARTMENT
#  @revert
  Scenario Outline: Revert department
    Given User has an unused department
    When User choose department "<name>" and choose revert
    And User choose use status as "Sử dụng"
    Then User should found department with "<name>" and "<note>" in the list
    Examples:
      | name        | note             |
      | Phòng ban 1 | phòng ban 1 note |

# SEARCH DEPARTMENT
#  @search
  Scenario Outline: Search department with invalid value
    When User search a department with "<name>" and "<status>"
    Then User should not found department with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name        | note            | status        |
      | inValid     | invalid note    | Sử dụng       |

#  @search
  Scenario Outline: Search department with valid value
    When User search a department with "<name>" and "<status>"
    Then User should found department with "<name>" and "<note>" in the list
    And Close browser

    Examples:
      | name            | note                | status        |
      | Phòng ban 1     | phòng ban 1 note    | Sử dụng       |
      | Phòng ban 2     | phòng ban 2 note    | Sử dụng       |

#  UPDATE DEPARTMENT ******************************************
#  @update
  Scenario Outline: Update department successful with valid value
    When User choose department "<name>" and choose update
    Then Open department form
    And Department form fields "<name>" and "<note>" are loaded by default
    When User enter department name "<name_updated>" and note "<note_updated>"
    And User click on save button in department form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And User should found department with "<name_updated>" and "<note_updated>" in the list
    And Close browser
    Examples:
      | name        | note             | name_updated            | note_updated               |
      | Phòng ban 1 | phòng ban 1 note | Phòng ban 1 - updated   | phòng ban 1 note - updated |

#  @update
  Scenario Outline: Update department failed with name is blank
    When User choose department "<name>" and choose update
    Then Open department form
    And Department form fields "<name>" and "<note>" are loaded by default
    When User clear department name
    And User enter note "<note_updated>"
    And User click on save button in department form
    Then Display alert message "Bắt buộc" for required values in department form

    Examples:
      | name        | note             | note_updated               |
      | Phòng ban 2 | phòng ban 2 note | phòng ban 2 note - updated |

#  @update
  Scenario Outline: Update department failed with name already exist
    When User choose department "<name>" and choose update
    Then Open department form
    And Department form fields "<name>" and "<note>" are loaded by default
    When User enter department name "<name_updated>" and note "<note_updated>"
    And User click on save button in department form
    Then Display alert message "Đã tồn tại" for required values in department form
    Examples:
      | name        | note             | name_updated    | note_updated               |
      | Phòng ban 3 | phòng ban 3 note | Phòng ban 2     | phòng ban 2 note - updated |

#  @update
  Scenario Outline: Update when no change value
    When User choose department "<name>" and choose update
    Then Open department form
    And Department form fields "<name>" and "<note>" are loaded by default
    And User click on save button in department form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And User should found department with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name        | note             |
      | Phòng ban 3 | phòng ban 3 note |

#  @update
  Scenario Outline: Update failed when canceling create operation
    When User choose department "<name>" and choose update
    Then Open department form
    And Department form fields "<name>" and "<note>" are loaded by default
    When User enter department name "<name_updated>" and note "<note_updated>"
    And User click on close button in department form
    And User should not found department with "<name_updated>" and "<note_updated>" in the list
    And Close browser
    Examples:
      | name        | note             | name_updated            | note_updated               |
      | Phòng ban 3 | phòng ban 3 note | Phòng ban 3 - updated   | phòng ban 3 note - updated |