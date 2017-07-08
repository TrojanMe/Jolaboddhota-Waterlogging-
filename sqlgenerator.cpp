#include<bits/stdc++.h>
#include <iostream>
#include <fstream>
#define rep(i,s,e) for(int i=s;i<e;i++)

using namespace std;

int main()
{
      string sop[] = {"1 feet","2 feet","3 feet" };
      string cs[] = {"Too much water","No comment ","Need fix now" , " " };
      ofstream myfile;
      myfile.open ("sqlq.txt");
      int x, y , a,b,ce , p , ud , cd , o;
      char c = 'a';
      rep(i,1,3000)
      {
        x = (rand()%2500)+10;
        y = (rand()%2500)+10;
        p = (rand()%25000)+1000;
        a = (rand()%26)+1;
        b = (rand()%60)+1;
        ud = (rand()%400)+1;
        cd = rand()%20+2;
        o = rand()%3;
        ce= rand()%4;
        myfile << "INSERT INTO `data` (`dataid`, `userid`, `locx`, `locy`, `customdata`, `custommode`, `approximatedata`, `time`, `comment`) VALUES (" ;
        myfile << i << " ," << ud << ", "  << x << " , " << y << " ," << cd  << " ," << "'inch' , '" << sop[o] <<"','" << a << ":" << b << "','" << cs[ce] << "');\n";
      }

      /*myfile << "INSERT INTO `user` (`userid`, `email`, `password`, `locx`, `locy`, `address`) VALUES (" ;
      myfile << i << " , '" << (char)(c+a) << "' , "  << p << " ," << x << " , " << y << " ,'" << (char)(c+b) << "');\n"; */
      myfile.close();
      return 0;
}
