import re

def fix(file):
    with open(file, 'r') as f:
        content = f.read()
    
    content = content.replace("import java.util.*;", 
        "import java.util.Map;\nimport java.util.HashMap;\nimport java.util.List;\nimport java.util.ArrayList;\nimport java.util.Collections;\nimport java.util.Scanner;\n")
    
    with open(file, 'w') as f:
        f.write(content)

fix('PA12_Ex1.java')
fix('PA12_Ex2.java')
