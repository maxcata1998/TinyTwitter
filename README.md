# Tiny Twitter



## Architecture

- Spring boot framework with command line entrypoint and datasource auto-configuration
- Mybatis annotation for query and result mapping
- Independent menu control module to reuse menu structure and isolate service implement with user interface

## Feature
- Auto menu set up
  - if you haven't logined in, you can't access 'my blogs', 'my collections' and 'make comment'
  - if you have made comment, you can only 'update comment' or 'delete comment', otherwise you can only 'add comment'
- Statistics
  - User information statistics about how many blog and comment have been written
  - Blog information statistics about how many people liked or collect each blogs
- Complex blog search function
  - Accepts fuzzy search on author name, blog title and blog content
  - Accepts conditions like whether blogs are liked or collected by user
  - User can choose to select each search condition

## How to run

1. Run the TinyTwitter_dump.sql in mysql to initiate the database
2. Go to folder that contains tiny-twitter-0.0.1-SHANSHOT.jar
3. Run with the following command, change username and password to yours.
```shell
java -jar tiny-twitter-0.0.1-SHANSHOT.jar --db-username=<username> --db-password=<password>
```
4. Now you can explore the functions of tiny twitter!

If you want to run source code in IDE, add "--db-username=<username> --db-password=<password>" to your program start arguments
