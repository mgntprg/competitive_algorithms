import time

saved = None # saved pathways to avoid recalculations

def solution(grid):
    global saved
    # Make 2D matrix, with each character taking up one slot, and each word taking up one row
    grid = [[char for char in word] for word in grid]
    saved = [[-1 for char in word] for word in grid] # Save num of paths -- dynamic programming

    a_paths = []
    for r in range(len(grid)):
        for c in range(len(grid[r])):
            a_paths += [rec(grid, r, c)]

    return max(a_paths)


def rec(grid, r, c):
    items = [] # Positions of subsequent letters

    # Add all subsequent letters in a 3x3 radius to items
    for r2 in range(r - 1, r + 2):
        for c2 in range(c - 1, c + 2):
            if r2 < len(grid) and r2 >= 0 and c2 < len(grid[r2]) and c2 >= 0 and ord(grid[r2][c2]) == ord(grid[r][c]) + 1:
                items.append( (r2, c2) )

    # Find longest path
    if not items: # if empty, then no more subsequent letters
        return 1
    else:
        maximum = 0
        for tup in items:
            r, c = tup[0], tup[1]
            if saved[r][c] != -1: # previously saved
                paths = saved[r][c]
            else:
                paths = rec(grid, tup[0], tup[1])
                saved[r][c] = paths # save value to avoid future calculations
            if paths > maximum:
                maximum = paths
    return maximum + 1

t1 = time.time()
print(solution(["A" ]))
print(solution(["BCDEFGHIJKLMNOPQRSTUVWXYZ"]))
print(solution([ "C", "D", "B", "A" ]))
print(solution(["KCBVNRXSPVEGUEUFCODMOAXZYWEEWNYAAXRBKGACSLKYRVRKIO", "DIMCZDMFLAKUUEPMPGRKXSUUDFYETKYQGQHNFFEXFPXNYEFYEX", "DMFRPZCBOWGGHYAPRMXKZPYCSLMWVGMINAVRYUHJKBBRONQEXX", "ORGCBHXWMTIKYNLFHYBVHLZFYRPOLLAMBOPMNODWZUBLSQSDZQ", "QQXUAIPSCEXZTTINEOFTJDAOBVLXZJLYOQREADUWWSRSSJXDBV", "PEDHBZOVMFQQDUCOWVXZELSEBAMBRIKBTJSVMLCAABHAQGBWRP", "FUSMGCSCDLYQNIXTSTPJGZKDIAZGHXIOVGAZHYTMIWAIKPMHTJ", "QMUEDLXSREWNSMEWWRAUBFANSTOOJGFECBIROYCQTVEYGWPMTU", "FFATSKGRQJRIQXGAPLTSXELIHXOPUXIDWZHWNYUMXQEOJIAJDH", "LPUTCFHYQIWIYCVOEYHGQGAYRBTRZINKBOJULGYCULRMEOAOFP", "YOBMTVIKVJOSGRLKTBHEJPKVYNLJQEWNWARPRMZLDPTAVFIDTE", "OOBFZFOXIOZFWNIMLKOTFHGKQAXFCRZHPMPKGZIDFNBGMEAXIJ", "VQQFYCNJDQGJPYBVGESDIAJOBOLFPAOVXKPOVODGPFIYGEWITS", "AGVBSRLBUYOULWGFOFFYAAONJTLUWRGTYWDIXDXTMDTUYESDPK", "AAJOYGCBYTMXQSYSPTBWCSVUMNPRGPOEAVVBGMNHBXCVIQQINJ", "SPEDOAHYIDYUJXGLWGVEBGQSNKCURWYDPNXBZCDKVNRVEMRRXC", "DVESXKXPJBPSJFSZTGTWGAGCXINUXTICUCWLIBCVYDYUPBUKTS", "LPOWAPFNDRJLBUZTHYVFHVUIPOMMPUZFYTVUVDQREFKVWBPQFS", "QEASCLDOHJFTWMUODRKVCOTMUJUNNUYXZEPRHYOPUIKNGXYGBF", "XQUPBSNYOXBPTLOYUJIHFUICVQNAWFMZAQZLTXKBPIAKXGBHXX" ]))
print(solution(["EDCCBA", "EDCCBA" ]))
print(solution([ "AMNOPA", "ALEFQR", "KDABGS", "AJCHUT", "AAIWVA", "AZYXAA" ]))
t2 = time.time()
print('Took' ,  round(t2 - t1, 5) , 'seconds')  