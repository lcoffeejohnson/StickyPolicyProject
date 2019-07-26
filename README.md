# StickyPolicyProject

This project models a User with a policy regarding the use of personal data and a ServiceProvider who handles that data. Multiple Users may be created to interact with the Service provider, but all ServiceProvider objects will interact with the same databse and use the same Server and CLient.

##To run this project:

1. Open two separate terminal windows (one for the ServiceProvider and one for the User)
2. Run the ServiceProvider first:
```
     $ javac ServiceProvider.java
     $ java ServiceProvider <Server port number> <Client IP address> <Client port number>
```
3. Next, run the User
```
      $ javac User.java
      $ java User <Server port number> <Client IP address> <Client port number>
```
