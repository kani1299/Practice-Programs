/******************************************************************************

                              Online C++ Compiler.
               Code, Compile, Run and Debug C++ program online.
Write your code in this editor and press "Run" button to compile and execute it.

*******************************************************************************/

#include <iostream>
#include<bits/stdc++.h>

using namespace std;

int main()
{
    vector<int> v1 {10,20,30,40,50};
    v1.push_back(45);
    v1.reserve(1000);//optimisation trick
    cout<<v1.size()<<" "<<v1.capacity();
    return 0;
}
