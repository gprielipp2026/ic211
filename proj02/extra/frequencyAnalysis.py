#!/usr/bin/env python3

totalChars = 0
counts = {} # match char:# occurrences

with open("am-en-cracklib", "r") as f:
    for char in ''.join([x.strip() for x in f.readlines()]):
        totalChars += 1
        if char in counts:
            counts[char] += 1
        else:
            counts[char] = 1

with open("frequencies.txt", "w") as f:
    for k in counts:
        counts[k] = float(counts[k]) / float(totalChars)
        f.write(k + " " + str(counts[k]) + "\n");


