This is a project with API tests sample using RestAssured and TestNG.

**Assumptions:** 
- Search results are present for words with length 3 and more symbols.
- Both Latin and Cyrillic symbols are supported as well as digits and special symbols.
- When less than 3 symbols are entered an empty array is returned.
- When empty string is used as a keyword the request is processed with 404 status code.
- When request is sent to an invalid endpoint 404 status code is returned too.

Implemented test cases:

**Case 1. Send GET request to valid URL with relevant keyword.**

GIVEN a valid page URL 'https://www.vodafone.ua/api/search/' and relevant keyword

WHEN GET request is sent to: 
https://www.vodafone.ua/api/search/{keyword}

THEN the request is processed with status code 200

AND content type is JSON

AND the response body contains the specified {keyword} in 'title' or 'short_description' in any data item


**Case 2. Send GET request to valid URL with a single character**

GIVEN a valid page URL 'https://www.vodafone.ua/api/search/' and a single character

WHEN GET request is sent to:
https://www.vodafone.ua/api/search/{s}

THEN the request is processed with status code 200

AND an empty array is returned


**Case 3. Send GET request to valid URL with empty string as a keyword**

GIVEN a valid page URL 'https://www.vodafone.ua/api/search/'

WHEN GET request is sent to the specified URL with an empty string as a keyword:
https://www.vodafone.ua/api/search/{}

THEN the request is processed with status code 404

**Case 4. Send GET request to valid URL with a keyword consisting of random characters**

GIVEN a valid page URL 'https://www.vodafone.ua/api/search/' and string with random characters

WHEN GET request is sent to the given URL:
https://www.vodafone.ua/api/search/{@bracadabra}

THEN the request is processed with status code 200

AND an empty array is returned


**Case 5. Send GET request to invalid URL**

GIVEN an invalid page URL 'https://www.vodafone.ua/api/searchh'

WHEN GET request is sent the given URL

THEN the request is processed with status code 404