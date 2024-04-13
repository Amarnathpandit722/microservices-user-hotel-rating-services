
Microservices System README Overview

   
This system comprises several microservices designed to provide various functionalities related to hotels, users, ratings, and more. It employs Okta for authentication and authorization, ensuring secure access to the services. The Config Server stores common application configurations, and the Registry Service registers microservices to enable service discovery via Eureka

Microservices:

API Gateway: Entry point for clients, routing requests to appropriate microservices.
Config Server: Stores and manages common application configurations.
Hotel Service: Manages hotel-related data and operations.
User Service: Handles user-related functionalities.
Registry Service: Registers microservices to facilitate service discovery using Eureka.
Rating Service: Deals with rating-related operations.
Authentication and Authorization:
Okta: Utilized for secure authentication and authorization.
Access Token: Users must obtain an access token from the API Gateway before accessing other microservices.

Setup:

Clone the Repositories: Clone the repositories for each microservice onto your local machine.
Setup Okta: Configure Okta for authentication and authorization. Obtain the necessary credentials and configure them in each microservice.
Configure Config Server: Update the Config Server with common application configurations.
Build Microservices: Build each microservice using the provided build scripts or IDE.
Deploy Microservices: Deploy each microservice to your preferred environment.
Access Control: Ensure that access to microservices is controlled via Okta and access tokens obtained from the API Gateway.

Usage:

Obtain Access Token: Users must first obtain an access token from the API Gateway by authenticating via Okta.
Access Microservices: Once authenticated and authorized, users can access the functionalities provided by each microservice by including the access token in the request headers.
Service Discovery: Microservices register with the Registry Service for service discovery using Eureka. Clients can discover and access services dynamically.

Additional Notes:

Configurations: Keep configurations consistent across microservices by storing them centrally in the Config Server.
Security: Ensure that security measures, such as HTTPS, are implemented to safeguard communications between services and clients.
Monitoring and Logging: Implement monitoring and logging solutions to track and troubleshoot service performance and issues.
Scalability: Design microservices with scalability in mind to handle increasing loads efficiently.

Contributors:

Amarnath Pandit
Thanks

Happy Coding !!!!
