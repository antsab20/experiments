#include <string>
#include <fstream>
#include <stack>
#include <list>
#include <iterator>
#include <algorithm>
#define ALPHABET_SIZE 26

int N, M;
std::ifstream fin("words.txt");
std::ofstream fout("output.txt");

class Trie {
private:
    struct node {   //Trie nodes
        bool isEnd;  //true if word
        node *child[ALPHABET_SIZE];
    } *root, *runner;

    struct maze_position {
        int up, right;   //up, right must be <= 3
        node *maze_runner;  //saves position of a Trie node
    };
    
    std::stack<maze_position> backtrack;    //saves path from start(x,y)
    std::stack<char> word_forming;  //saves letters (Trie nodes indexes)
    std::list<std::pair<int, int> > visitor; //visited nodes record
    std::list<std::string> duplicates; //doesn't allow duplicates
    maze_position curr_pos; //current position in grid

public:
    Trie(void) {
        root = new node();
        root -> isEnd = false;
        runner = root;
        curr_pos.up = curr_pos.right = 1;
        curr_pos.maze_runner = NULL;
    }
    ~Trie(void) {purge(root);}

    void purge(node *current) {
        for(int i = 0; i < ALPHABET_SIZE; i++)
            if(current -> child[i] != NULL)
                purge(current -> child[i]);

        delete current;
    }

    void insert(std::string word) {
        node *current = root;
        for(unsigned int i = 0; i < word.length(); i++) {
            const int letter = (int)word[i] - (int)'a';
            if(current -> child[letter] == NULL)
                current -> child[letter] = new node();
            current = current -> child[letter];
        }
        current -> isEnd = true;
    }
    
    void workhorse(int x, int y, char **grid, bool **visited) {
        if(!find(grid[x][y])) return; //if first letter doesn't exist
        
        runner = runner -> child[(int)grid[x][y] - (int)'a']; //goto first child    
        curr_pos.maze_runner = runner;
        curr_pos.up = curr_pos.right = 1;
        word_forming.push(grid[x][y]); //add first letter
        
        int i, j; 
        i = x;
        j = y;
      
        while(true) {
            //Where 2 is current position, 1 visited and 0 unvisited
            //2 0 0 ->  1 2 0 or 1 2 0 -> 1 1 2 
            while(j+1 < M && !visited[i][j+1] && curr_pos.right < 3 
                          && find(grid[i][j+1])) { //RIGHT LOOP
                j++;
                backtrack.push(curr_pos); //saves last position
                runner = runner -> child[(int)grid[i][j] - (int)'a'];
                word_forming.push(grid[i][j]);
                curr_pos.right++;
                curr_pos.up = 1;
                curr_pos.maze_runner = runner;
                visited[i][j] = true;
                visitor.push_back(std::make_pair(i,j));
                if(runner -> isEnd) 
                    write_word(x, y);
            }
            //Where 2 is current position, 1 visited and 0 unvisited
            //0     0        0      0        0       2
            //0  -> 2        2 0 -> 1 2      2 1 ->  1 1
            //2 1   1 1      1 1    1 1      1 1     1 1
            while(i > 0 && curr_pos.up < 3 && find(grid[i-1][j]) 
                        && !visited[i-1][j]) { //UP LOOP
                unvisit(i, visited);
                i--;
                backtrack.push(curr_pos);  //saves last position
                runner = runner -> child[(int)grid[i][j] - (int)'a'];
                word_forming.push(grid[i][j]);
                curr_pos.right = 1;
                curr_pos.up++;
                curr_pos.maze_runner = runner;
                visited[i][j] = true;
                visitor.push_back(std::make_pair(i,j));
                if(runner -> isEnd)
                    write_word(x,y);
                if(j+1 < M && !visited[i][j+1] && curr_pos.right < 3 
                          && find(grid[i][j+1]))
                    break;
            }
            
            //If stuck -> start backtracking           
            //0 1 1       0 1 1        0 1 1        2 1 1
            //1 1 2 1  -> 1 2 1 1  ->  2 1 1 1  ->  1 1 1 1
            while(true) {
                if(i < 1 || !find(grid[i-1][j]) || curr_pos.up >= 3 
                         || visited[i-1][j]) {

                    if(j+1 >= M || visited[i][j+1] || curr_pos.right >= 3
                              || !find(grid[i][j+1])) {
                        
                        if(!backtrack.empty()) {
                            maze_position save = backtrack.top();
                            if(save.up < curr_pos.up)
                                i++;
                            else if(save.right < curr_pos.right)
                                j--;
                            curr_pos = save;
                            backtrack.pop();
                            word_forming.pop();
                            runner = curr_pos.maze_runner;
                        }
                        else { //THE END
                            runner = root; //reset runner
                            unvisit(x+1, visited); //unvisit all visited nodes
                            duplicates.clear();
                            std::stack<char> empty; //empty stack;
                            std::swap(word_forming, empty);
                            return;
                        }
                     }
                     else break;
                }
                else break;    
             }
    }}

    //if current prefix + letter makes prefix/word returns true
    bool find(char letter) {
        if(runner -> child[(int)letter - (int)'a'] != NULL) 
            return true;
        else return false;
    }

    //The tricky part so far.
    //Basically what this method is doing is iterating through the list
    //erasing and "unvisting" the nodes that are "lower" than X
    //Example: Let 1 be visited, 0 unvisited and 2 current position
    //  0 0 0 0 0                     0 0 0 0 0             0 0 0 0 0 
    //  0 0 0 0 2                     0 0 0 0 1             0 0 0 0 0 
    //  0 0 0 0 1  Got  => backtrack  0 0 0 0 1  unvisit=>  0 0 0 0 0 
    //  0 0 1 1 1  stuck              0 0 1 2 1             0 0 1 2 1 
    //  1 1 1 0 0                     1 1 1 0 0             1 1 1 0 0 
    void unvisit(int x, bool **visited) {
        std::list<std::pair<int,int> >::iterator it;
        for (it = visitor.begin(); it != visitor.end();)
            if(it -> first < x) {
                visited[it -> first][it -> second] = false;
                it = visitor.erase(it);
            }
            else it++;  
    }

    //constructing the word and writing it out
    void write_word(int x, int y) {
        std::stack<char> s = word_forming;
        std::string word = "";
        
        while(!s.empty()) {
            word += s.top();
            s.pop();
        }
        std::reverse(word.begin(), word.end());

        std::list<std::string>::iterator it;
        for(it = duplicates.begin(); it != duplicates.end(); it++)
            if(word == *it) return;
        
        duplicates.push_back(word);
        fout << y << "," << x << "," << word << "\n";
    }
};


int main(void) {
    std::string line;
    Trie words;

    if(fin.is_open()) {
        while(getline(fin, line))
            words.insert(line);
        fin.close();
    }
    else return 1; //error, can't open file "words.txt"

    fin.open("grid.txt");
    getline(fin, line);
    M = line.length();
    fin.seekg(0, fin.end);
    N = fin.tellg() / line.length();
    fin.seekg(0, fin.beg);

    char **grid;
    bool **visited;
    grid = new char*[N];
    visited = new bool*[N];
    for(int i = 0; i < N; i++) {
        grid[i] = new char[M];
        visited[i] = new bool[M];
    }

    for(int i = 0; i < N; i++)
        for(int j = 0; j < M; j++) {
            fin >> grid[i][j];
            visited[i][j] = false;
        }
    
    for(int i = 1; i < N; i++)
        for(int j = 0; j < M; j++)
            words.workhorse(i, j, grid, visited);
    
    for(int i = 0; i < N; i++) {
        delete[] grid[i];
        delete[] visited[i];
    }
    delete[] grid;
    delete[] visited;

    fin.close();
    fout.close();
    return 0;
}
