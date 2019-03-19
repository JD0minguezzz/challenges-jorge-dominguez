import csv
import random

cities = ["Biwzienu", "Tiduwomu", "Ticuevo", "Zogama", "Pitpilfel", "Icabohet", "Holibalis", "Jihebcod", "Kaejuzed", "Enwaila",
"Gibuhwuj", "Umbasfuc", "Lilakhom", "Ginaken", "Ekujizaz", "Onikaji", "Pohacaf", "Cuwijkol", "Atalafof", "Vatabe", "Lugesos",
"Keronaw", "Azigukvod", "Zabmunuc", "Afizokef", "Liphiek", "Sacsisli", "Ririwbob", "Zavlosso", "Zugiveb", "Hihepbek", "Fudikor",
"Avikessu", "Kegouwa", "Setibane"]

first = ["Della", "Troy", "Gene", "Luke", "John", "Shawn", "Nettie", "Kyle", "Kevin", "Rena", "Elsie", "Eric", "Chester", "Elva", 
"Devin", "Bobby", "Eddie", "Victor", "Chester", "Gordon", "Jack", "Alvin", "Katharine", "Garrett", "Bessie", "Danny", "Nancy", "Douglas", 
"Lucille", "Miguel", "Blanche", "Katie", "Patrick", "Bess", "Jose", "Rosetta", "Mary", "Kate", "Stanley", "Mable"]

name = ["Frank Moore", "Polly Lucas", "George Massey", "Henrietta Hansen", "Ruby Hammond", "Cora Roberts", "Hettie Blake", "Danny Roberson",
"Philip Jacobs", "Thomas Lloyd", "Christina Lee", "Katie Hanson", "Mabel Burton", "Willie Munoz", "Gordon Munoz" "Tom Fisher", "Jeffrey Hunt", "Bernard Hayes", 
"Janie Fisher", "Ralph Perez", "Lily Hayes", "Rose McLaughlin", "Gordon Jacobs", "Evelyn Vargas", "Leonard Reed", "Marvin Paul", "Adeline Reid",
"Matthew Lane", "Mark Gomez", "Virgie Perez", "Russell McLaughlin", "Robert McCarthy", "Josie Curtis", "Rose Newton", "Samuel Quinn",
"Katie Silva", "Bryan Bell", "Glen Elliott", "Etta Hampton", "Lucy Lucas"]

text = "Lufav jug vendube udmavir jesdah roj oza uwlicbo hokjopu av cukim gusamjo monig to evhapon rimumgok titko sernauk. Fe ubvek erwa jamojo fidhozka ebine hospo cuvmaji jemwuf akorater ugjinwel nilod. Bu ezimek vubiv zari ammapup ugpej wo guwad ugokoiz eno ran ovuvu bu dumonvoj."

sentence = "Obo uhralfam vomlude afbop ujicuw wizewibiw juz niwu wihug jiipeno oguhos hulvawho er ub lizsunu fi wuzibij wugilfas."

with open('Registers100000.csv', 'w', newline='') as csvfile:
    writer = csv.writer(csvfile, delimiter=',')
    seq = 1000001
    day = 0
    month = 0
    for i in range(100000):
        month = random.randint(1,12)

        if month == 4 or month == 6 or month == 9 or month == 11:
            day = random.randint(1,30)
        else:
            day = random.randint(1,31)
        
        if month == 2:
            day = random.randint(1,28)

        birthday = [str(month) +"/"+ str(day) +"/"+ str(random.randint(1953,2000))]

        date = [str(random.randint(18,65)) +"/"+ str(month) +"/"+ str(day)]

        trgr = seq + 100

        dollar = ["$" + str(random.randint(0,10000)) + "." + str(random.randint(1,9)) + str(random.randint(1,9))]
        writer.writerow([seq] + 
                        random.choices(['Y', 'N']) + 
                        [random.randint(18,65)] +
                        birthday + 
                        random.choices(['TRUE', 'FALSE']) + 
                        random.choices(cities) + 
                        [random.randint(30000000000000, 6380000000000000)] +
                        date + 
                        [random.randint(3,100000000000000000000)] +
                        dollar + 
                        random.choices(first) +
                        [random.randrange(-900717000000, 900717000000)] +
                        random.choices(name) +
                        random.choices(first) +
                        [text] +
                        [sentence] + 
                        [trgr])
        seq += 1