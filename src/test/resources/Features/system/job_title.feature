#noinspection NonAsciiCharacters

@UAT_Testing
Feature: Job title

  Background:
    Given User is on "Chức danh" page with account "admin" and password "123456@#"

#  CREATE JOB TITLE ******************************************
###  @create @update  @delete  @revert @search @read
  @update
  Scenario Outline: Create job title successful with valid value
    When User click on create job title button
    Then Open job title form
    When User enter job title name "<name>" and note "<note>"
    And  User click on save button in job title form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And  User should found job title with "<name>" and "<note>" in the list
    And  Close browser
    Examples:
      | name                     | note           |
      | Quản trị hệ thống        | jobt note      |
      | Giám đốc nhà máy         | jobt note      |
      | Phụ trách mua hàng       | jobt note      |

#  @create
  Scenario: Create job title failed with name is blank
    When User click on create job title button
    Then Open job title form
    When User enter job title note "jobt note"
    And User click on save button in job title form
    Then Display alert message "Bắt buộc" for required values in job title form
    And Close browser

#  @create
  Scenario Outline: Create job title failed with name already exist
    When User click on create job title button
    Then Open job title form
    When User enter job title name "<name>" and note "<note>"
    And User click on save button in job title form
    Then Display alert message "Đã tồn tại" for required values in job title form
    And Close browser
    Examples:
      | name                     | note      |
      | Quản trị hệ thống        | jobt note |

#  @create
  Scenario Outline: Create job title failed when canceling create operation
    When User click on create job title button
    Then Open job title form
    When User enter job title name "<name>" and note "<note>"
    When User click on close button in job title form
    Then Close job title form
    And User should not found job title with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name                   | note      |
      | Nhân viên mua hàng     | jobt note |


# READ JOB TITLE ******************************************
#  @read
  Scenario Outline: Read job title
    When User choose job title "<name>" and choose view
    Then Open job title form
    And Job title form fields are read-only
    And Job title form fields "<name>" and "<note>" are loaded by default
    When User click on close button in job title form
    Then Close job title form
    And Close browser

    Examples:
      | name                     | note       |
      | Quản trị hệ thống        | jobt note  |


# DELETE JOB TITLE

#  @delete @revert
  Scenario Outline: Delete job title
    When When user choose job title "<name>" and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on yes button
    Then Display alert message as "Xóa thành công"
    When User click on confirm button
    Then Close alert message
    And User should not found job title with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name                     | note           |
      | Quản trị hệ thống        | jobt note      |


#  @delete
  Scenario Outline: Delete failed when canceling create operation
    When When user choose job title "<name>" and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on no button
    Then Close alert message
    And User should found job title with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name                     | note           |
      | Giám đốc nhà máy         | jobt note      |

# REVERT JOB TITLE
#  @revert
  Scenario Outline: Revert job title
    Given User has an unused job title
    When User choose job title "<name>" and choose revert
    And User choose use status "Sử dụng" for job title
    Then User should found job title with "<name>" and "<note>" in the list
    Examples:
      | name                     | note           |
      | Quản trị hệ thống        | jobt note      |

    # SEARCH JOB TITLE
#  @search
  Scenario Outline: Search department with valid value
    When User search a job title with "<name>" and "<status>"
    Then User should found job title with "<name>" and "<note>" in the list
    And Close browser

    Examples:
      | name                    | note         | status     |
      | Quản trị hệ thống       | jobt note    |Sử dụng     |

#  @search
  Scenario Outline: Search job title with invalid value
    When User search a job title with "<name>" and "<status>"
    Then User should not found job title with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name        | note            | status        |
      | inValid     | invalid note    | Sử dụng       |



#  UPDATE JOB TITLE ******************************************
  @update
  Scenario Outline: Update job title successful with valid value
    When User choose job title "<name>" and choose update
    Then Open job title form
    And Job title form fields "<name>" and "<note>" are loaded by default
    When User enter job title name "<name_updated>" and note "<note_updated>"
    And User click on save button in job title form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And User should found job title with "<name_updated>" and "<note_updated>" in the list
    And Close browser
    Examples:
      | name                    | note         | name_updated                    | note_updated        |
      | Quản trị hệ thống       | jobt note    | Quản trị hệ thống - update      | jobt note - update  |

  @update
  Scenario Outline: Update job title failed with name is blank
    When User choose job title "<name>" and choose update
    Then Open job title form
    And Job title form fields "<name>" and "<note>" are loaded by default
    When User clear job title name
    When User enter job title note "<note_updated>"
    And User click on save button in job title form
    Then Display alert message "Bắt buộc" for required values in job title form

    Examples:
      | name                | note           | note_updated        |
      | Giám đốc nhà máy    | jobt note      | jobt note - updated |

  @update
  Scenario Outline: Update job title failed with name already exist
    When User choose job title "<name>" and choose update
    Then Open job title form
    And Job title form fields "<name>" and "<note>" are loaded by default
    When User enter job title name "<name_updated>" and note "<note_updated>"
    And User click on save button in job title form
    Then Display alert message "Đã tồn tại" for required values in job title form
    Examples:
      | name                | note        | name_updated         | note_updated    |
      | Phụ trách mua hàng  | jobt note   | Giám đốc nhà máy     | jobt note       |

  @update
  Scenario Outline: Update when no change value
    When User choose job title "<name>" and choose update
    Then Open job title form
    And Job title form fields "<name>" and "<note>" are loaded by default
    And User click on save button in job title form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And User should found job title with "<name>" and "<note>" in the list
    And Close browser
    Examples:
      | name                | note        |
      |  Giám đốc nhà máy   | jobt note   |

  @update
  Scenario Outline: Update failed when canceling create operation
    When User choose job title "<name>" and choose update
    Then Open job title form
    And Job title form fields "<name>" and "<note>" are loaded by default
    When User enter job title name "<name_updated>" and note "<note_updated>"
    And User click on close button in job title form
    And User should not found job title with "<name_updated>" and "<note_updated>" in the list
    And Close browser
    Examples:
      | name                | note       | name_updated                 | note_updated        |
      | Giám đốc nhà máy    | jobt note  | Giám đốc nhà máy - updated   | jobt note - updated |
