# Suspicious Content Detection
### Intro
This project contains a java implementation of a hybrid approach, which consists in relying as much on the advantages of machine learning (Naive Bayes classifier) as on the advantages of fuzzy logic, that can help on the detection of suspicious content published in social media and by suspicious we mean everything related to terrorism, internal security, dangerous materials, Health concern, infrastructure security, violence / suicide, disaster / emergencies and cyber security.
### Quick project files description
- **allnames.txt**, **worldsurnames.txt** and **stopwords.txt** contains the words that are irrelevent on the learning phase (you can add as much words as you like and they'll be ignored).
- **fuzzy.fcl** contains the rules of our fuzzy inference system.
- **mapping.txt** the learning databases were in english so I added this mapping file so the system can learn from non english words. Each column in the file represent a language.
- **normal.txt** and **suspicious.txt** are the learning databases.
- **testNormal.txt** and **testSuspicious** contains tweets I classified myself to validate the model.
