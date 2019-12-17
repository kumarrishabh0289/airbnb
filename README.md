# OpenHome 

OpenHome provides a platform for hosts to accommodate guests with short-term lodging and tourism-related activities. Customers can search for lodging using filters such as lodging type, dates, location, and price. Customers can search for specific types of homes, such as Condo and house, apartment, and Pent House. Before booking, users must provide personal and payment information.

Further, the customer can do the reservation of that property, Once the user book the property, he can see all the details on the dashboard. On the dashboard, the user can check-in, check-out and even cancel the reservation. If user check-in late or cancel’s the reservation then appropriate changes get applied and the rest of the amount gets refunded. The user cannot check-in early. The same functionality provided to host where the host can cancel the reservation or change the availability of the property. But, the host needs to pay the charges then only he is able to cancel the reservation which is within a week. For all these actions, the appropriate party gets notified through the mail. The user also can see his monthly billing history for last 12 months.

The time advancement management is the part the application’s time can be moved in the future and all of the operations like check-out performed automatically. It also has Cron jobs added, once the customer is done with his reservation, the Cron job automatically does check-out process. It acts as a batch job for our application. As part of bonus features, the user can able to view the searched place on google map, also, the user can able to rate the property.

## URL to access the application.
   http://ec2-18-217-254-66.us-east-2.compute.amazonaws.com:3000/

## Members 

   | Name                           | SJSU ID    |             Email ID                  | 
   |--------------------------------|------------|---------------------------------------|
   | **Laxmikant Bhaskar Pandhare** | 013859989  |  laxmikantbhaskar.pandhare@sjsu.edu   |
   | **Prachi Chouksey**            | 013828945  |     prachi.chouksey@sjsu.edu          |
   | **Ayushi Singh**               | 013820807  |      ayushi.singh@sjsu.edu            | 
   | **Kumar Rishabh**              | 011457914  |      kumarrishabh@sjsu.edu            |
   

## High Level Design Diagram
![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/High%20Level%20Design%20Diagram.png)

## Build Instructions

Two folders are there: 

#### Frontend: 

Goto-> frontend->Quora and run run npm install ( it will install all dependency for this application) 

then npm start in cmd. 

#### Backend: 

import backend folder in eclipse or IntelliJ IDE. 

Import all the dependeccies required to run spring boot applciation and run the application.

## Bonus Features

As part of the bonus features, we have implemented reviews and rating features. In this, guests can give reviews from 1 to 5 stars for the property. At first, the property gets displayed with the average rating it got till date. The below screen prints has rating given by both guest and host. 

#### Guest and Host can give rating.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/Bonus%20Features.png)

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/host%20side%20rating.png)

#### Implemented Google Map feature.

Further, we have integrated google map functionality in our application. Once the user enters the location, then its location gets displayed on the google location. Google Map shows for the location entered below. Once you click on view larger map, you can see below.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/Map.png)

## Application
A glimpse of the application

#### Signup by guest or host.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/signup.png)

#### Login by guest or host.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/login.png)

#### Propety created by host.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/Home%20creation.png)

#### host Dashboard.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/created%20home.png)

#### Propety booked by guest after search.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/home%20booking.png)

#### Booking cancelled by guest.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/cancel%20by%20guest.png)

#### Booking cancelled by Host.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/Cancel%20by%20host.png)

#### Billing history.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/billing%20history.png)

#### For Every action host and guest get notified through email.

![alt text](https://github.com/kumarrishabh0289/airbnb/blob/laxmikant_new/images/email%20notification.png)
