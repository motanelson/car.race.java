list1=[]
def adds(lists,additem):
    lists=lists+[additem]
    return lists

def processs(content):
    lists=[]
    #get body only
    contentpos=content.find("<body")
    if contentpos<0:
        contentpos=content.find("<BODY")
    if contentpos>-1:
        content=content[contentpos:]
    contentpos=content.find("</body")
    
    if contentpos<0:
        contentpos=content.find("</BODY")
    if contentpos>-1:
        content=content[:contentpos]
    
    tags=content.split(">")
    for tag in tags:
        listtags=tag.split("<")
        count=0
        for ttag in listtags:
            ttag=ttag.strip()
            if count==1:
                lists=adds(lists,"<" + ttag + ">")
            else:
                if ttag!="":
                    lists=adds(lists,ttag)
            count+=1
    return lists
files=input("give me a .html file? ")
f1=open(files,"r")
contents=f1.read()
f1.close()
content=processs(contents)
print(content)


