def f(j, num):
    i_num_mx = 0
    i = 0
    i_num = 1
    while (i_num <= j):
        if j % i_num == 0:
            i_num_mx = i
        i += 1
        i_num *= num
    # print(j, num, i_num_mx)
    return i_num_mx


def isSpecial(j):
    return f(j, 6) > f(j, 7)

j = 0
x = set()
for i in range(2 * 10**5):
    if isSpecial(i):
        x.add(i//6)

for i in range(len(x)):
    if i not in x:
        print(i//7)
