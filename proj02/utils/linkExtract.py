#!/usr/bin/python3

with open("index.html", "r") as f:
    text = ""
    isOpen = False
    while line := f.readline():
        openInd = line.find("<a")
        closeInd = line.find("</a>")
        if openInd != -1 and closeInd != -1:
            print(line[openInd:closeInd])
        elif openInd != -1:
            isOpen = True
            text += line[openInd:]
        if isOpen:
            if closeInd != -1:
                isOpen = False
            text += line[:closeInd]
        if text != "" and openInd == -1 and closeInd == -1:
            print(text)
            text = ""
