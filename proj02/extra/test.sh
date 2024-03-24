#!/usr/bin/bash

#cat plainPswds.txt | xargs -I % bash -c "echo -e '% e "SUPER^secret[Message]533"'" | xargs -I % bash -c "java Caesar %" | cut -d " " -f4 > cipherText.txt
#cat cipherText.txt | xargs -I % bash -c "java CaesarCracker '%'" > cracked.txt
#cat cracked.txt

cat /usr/share/dict/american-english | xargs  -I % bash -c "java Caesar % e 'SUPER^secret[Message];533'"

