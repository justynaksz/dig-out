# DIG-OUT

Application is designed for cemetery management. 

## 1. Functionalities
***

### 1.1. Main page:
+ Displays deceased on his/her birthdate anniversary
  + find deceased by birthdate
+ Displays deceased on his/her death date anniversary
  + find deceased by death date
+ Searches for deceased with parameters
  + find decease by parameters
  + pagination
  + sorting
+ Find available graves grouped by cemetery
  + find grave without owner grouped by cemetery
  + pagination

### 1.2. Login page/tab:
+ Register new user
  + create app_user
+ Login
  + login app_user
+ Logout 
  + logout app_user

### 1.3. User profile:
+ Displays user data
  + find user by id
+ Update profile data (password, avatar, connect account with grave_owner)
  + update app_user
+ Delete user account
  + delete app_user (not grave owner)
+ Displays all graves connected with this user
  + find graves by grave_owner

### 1.4. Grave profile:
+ Displays grave data
  + find grave by id
+ Displays all deceased in this grave
  + find deceased by grave
+ Displays capacity info
  + calculates available places
+ Displays info about owner (true / false, no fragile data)
  + checks if grave has owner

### 1.5. Deceased profile:
+ Displays deceased data 
  + find deceased by id
+ Displays button to move to grave profile

### 1.5. Grave_owner tab (on app_user profile):
+ Displays grave_owner data
  + find grave_owner by id
+ Updates grave_owner data (address & phone number)

### 1.6. Admin* grave profile:
+ Displays button to update grave & localization data
  + update grave & localization
+ Displays button to delete grave & localization 
  + delete grave & localization

### 1.7. Admin* deceased profile:
+ Displays button to update deceased data
  + update deceased data
+ Displays button to delete deceased
  + delete deceased

### 1.8. Admin* grave_owner profile:
+ Displays button to update grave_owner data
  + update grave_owner data
+ Displays button to delete grave_owner
  + delete grave_owner

### 1.9. Admin* tabs:
+ Searches for profiles by parameters including id
  + find grave_owner
  + find grave & localization
  + find deceased
+ Creates new objects
  + create grave_owner 
  + create grave & localization
  + create deceased
