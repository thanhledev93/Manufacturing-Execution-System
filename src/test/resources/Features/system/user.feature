#noinspection NonAsciiCharacters

#@UAT_Testing
Feature: User

  Background:
    Given User is on "Người dùng" page with account "admin" and password "123456@#"

#  CREATE USER ******************************************
  @create @update  @delete  @revert @search @read
  Scenario Outline: Create user successful with valid value
    When User click on create user button
    Then Open user form
    When User enter firstname "<firstname>" and lastname "<lastname>"
    And User choose department "<dept>" and job title "<jobt>"
    And User enters username "<username>" and password "<password>"
    And  User click on save button in user form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    And  User should found user with "<firstname>" "<lastname>" "<dept>" "<jobt>" "<username>" in the list
    And  Close browser
    Examples:
      | firstname   | lastname  | dept         | jobt                    | username  | password  |
      | Đặng Minh   | Long      | Văn phòng    | Giám đốc nhà máy        | longdm    | 123       |
      | Đàm Văn     | Quân      | Kinh doanh   | Phụ trách kinh doanh    | quandv    | 123       |
      | Võ Giáp     | Tuất      | Kinh doanh   | Nhân viên kinh doanh    | tuatvg    | 123       |
      | Lê Thị      | An        | Kho          | Phụ trách kho           | anlt      | 123       |

  @create
  Scenario: Create user failed with username and password is blank
    When User click on create user button
    Then Open user form
    When User enter user info with
      | firstname    | lastname  | dept         | jobt                   |
      | Lê Thị      | An        | Kho          | Phụ trách kho           |
    And User click on save button in user form
    Then Display alert message "Bắt buộc" for required values in user form
    And Close browser

  @create
  Scenario: Create user failed with username already exist
    When User click on create user button
    Then Open user form
    When User enter user info with
      | firstname    | lastname  | dept         | jobt                | username  | password  |
      | Đặng Minh    | Long      | Văn phòng    | Giám đốc nhà máy    | longdm    | 123       |
    And  User click on save button in user form
    Then Display alert message "Đã tồn tại" for username in user form
    And Close browser

  @create
  Scenario: Create user failed when canceling create operation
    When User click on create user button
    Then Open user form
    When User enter user info with
      | firstname    | lastname  | dept         | jobt                   | username  | password  |
      | Nguyễn Mỹ   | Phượng    | Mua hàng     | Phụ trách mua hàng      | phuongnm  | 123       |
    And  User click on close button in user form
    Then Close user form
    Then  User should not found user in the list
      | firstname    | lastname  | dept         | jobt                   | username  |
      | Nguyễn Mỹ   | Phượng    | Mua hàng     | Phụ trách mua hàng      | phuongnm  |
    And  Close browser


## READ USER ******************************************
  @read
  Scenario: Read job title
    When User choose username as "longdm" and choose view
    Then Open user form
    And User form fields are read-only
    And User form fields are loaded by default
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đặng Minh   | Long      | Văn phòng    | Giám đốc nhà máy        | longdm    |

    When User click on close button in user form
    Then Close user form
    And Close browser

## DELETE USER
  @delete
  Scenario: Delete user
    When User choose username as "longdm" and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on yes button
    Then Display alert message as "Xóa thành công"
    When User click on confirm button
    Then Close alert message
    And  User should not found user in the list
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đặng Minh   | Long      | Văn phòng    | Giám đốc nhà máy        | longdm    |

    And Close browser

  @delete
  Scenario: Delete failed when canceling create operation
    When User choose username as "quandv" and choose delete
    Then Display alert message as "Bạn có chắc không"
    When User click on no button
    Then Close alert message
    And  User should found user in the list
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đàm Văn     | Quân      | Kinh doanh   | Phụ trách kinh doanh    | quandv    |
    And Close browser


#    # SEARCH JOB TITLE
  @search
  Scenario: Search user with lastname valid value
    When User search with lastname as "Quân"
    Then  User should found user in the list
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đàm Văn     | Quân      | Kinh doanh   | Phụ trách kinh doanh    | quandv    |

    And Close browser

  @search
  Scenario: Search user with username valid value
    When User search with username as "quandv"
    Then  User should found user in the list
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đàm Văn     | Quân      | Kinh doanh   | Phụ trách kinh doanh    | quandv    |

    And Close browser

  @search
  Scenario: Search user with invalid value
    When User search with lastname as "inValid"
    Then  User should not found user in the list
      | firstname | lastname     | dept       | jobt           | username  |
      | inValid   | inValid      | inValid    | inValid        | inValid   |
    And Close browser
#
#
#
##  UPDATE USER ******************************************
  @update
  Scenario: Update user successful with valid value
    When User choose username as "quandv" and choose update
    Then Open user form
    And User form fields are loaded by default for update
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đàm Văn     | Quân      | Kinh doanh   | Phụ trách kinh doanh    | quandv    |
    When User enter user info with
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đàm Văn updated     | Quân updated     | Sản xuất   | Nhân viên sản xuất    | quandv_upt    |
    And User click on save button in user form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    Then  User should found user in the list
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Đàm Văn updated     | Quân updated     | Sản xuất   | Nhân viên sản xuất    | quandv_upt    |
    And Close browser

  @update
  Scenario: Update user failed with name is blank
    When User choose username as "tuatvg" and choose update
    Then Open user form
    And User form fields are loaded by default for update
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Võ Giáp     | Tuất      | Kinh doanh   | Nhân viên kinh doanh    | tuatvg    |
    When User clear username and password
    And User click on save button in user form
    Then Display alert message "Bắt buộc" for required values in user form
#
  @update
  Scenario: Update user failed with name already exist
    When User choose username as "tuatvg" and choose update
    Then Open user form
    And User form fields are loaded by default for update
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Võ Giáp     | Tuất      | Kinh doanh   | Nhân viên kinh doanh    | tuatvg    |
    When User clear username and password
    And User enter user as "anlt" and password as "123"
    And User click on save button in user form
    Then Display alert message "Đã tồn tại" for username in user form

  @update
  Scenario: Update user successful when no change value
    When User choose username as "tuatvg" and choose update
    Then Open user form
    And User form fields are loaded by default for update
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Võ Giáp     | Tuất      | Kinh doanh   | Nhân viên kinh doanh    | tuatvg    |
    And User click on save button in user form
    Then Display alert message "Lưu thành công"
    When User click on confirm button
    Then Close alert message
    Then  User should found user in the list
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Võ Giáp     | Tuất      | Kinh doanh   | Nhân viên kinh doanh    | tuatvg    |
    And Close browser

  @update
  Scenario: Update user failed when canceling create operation
    When User choose username as "tuatvg" and choose update
    Then Open user form
    And User form fields are loaded by default for update
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Võ Giáp     | Tuất      | Kinh doanh   | Nhân viên kinh doanh    | tuatvg    |
    When User enter user info with
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Nguyễn Mỹ   | Phượng    | Mua hàng     | Phụ trách mua hàng      | phuongnm  |
    And User click on close button in user form
    Then  User should not found user in the list
      | firstname   | lastname  | dept         | jobt                    | username  |
      | Nguyễn Mỹ   | Phượng    | Mua hàng     | Phụ trách mua hàng      | phuongnm  |

    And Close browser
