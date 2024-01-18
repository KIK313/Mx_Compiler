#define bool _Bool
int printf(const char *restrict format, ... );
int scanf(const char * restrict format, ...);
int sprintf(char *str, const char* format, ...);
int strcmp(const char* s1, const char* s2);
unsigned long strlen(const char* ch);
void *malloc(unsigned long n);
void print(char* str) {
    printf("%s",str);
}
void println(char* str) {
    printf("%s\n",str);
}
void printInt(int x) {
    printf("%d",x);
}
void printlnInt(int x) {
    printf("%d\n",x);
}
char* getString() {
    char* ch = malloc(256);
    scanf("%s",ch);
    return ch;
}
int getInt() {
    int x;
    scanf("%d",&x);
    return x;
}
char* toString(int x) {
    char* ch = malloc(16);
    sprintf(ch, "%d", x);
    return ch;
}
int string_length(const char* ch) {
    return strlen(ch);
}
char* string_substring(const char* ch, int l, int r) {
    char* s = malloc(r-l+1);
    for (int i = l; i < r; i++) s[i-l] = ch[i];
    s[r-l] = '\0';
    return s;
}
int string_parseInt(const char* ch) {
    int st = 0, op = 1;
    if (ch[0] == '-') {
        st = 1;
        op = -1;
    }
    int x = 0;
    int len=strlen(ch);
    for (int i = st; i < len; ++i) {
        if (!(ch[i] >= '0' && ch[i] <= '9')) break;
        x = x * 10 + ch[i] - '0';
    }
    return x * op;
}
int string_ord(const char* ch, int pos) {
    return ch[pos];
}
char* string_add(const char* s1, const char* s2) {
    int l1 = strlen(s1); int l2 = strlen(s2);
    char *ch = malloc(l1+l2+1);
    for (int i = 0; i < l1; i++) ch[i] = s1[i];
    for (int i = 0; i < l2; i++) ch[i+l1] = s2[i];
    ch[l1+l2] = '\0';
    return ch;
}
int array_size(void* ptr) {
    return ((int *)ptr)[-1];
}
bool string_eq(const char* s1, const char* s2) {
    return (strcmp(s1, s2) == 0);
}
bool string_neq(const char* s1, const char* s2) {
    return (strcmp(s1, s2) != 0);
}
bool string_lt(const char* s1, const char* s2) {
    return (strcmp(s1, s2) < 0);
}
bool string_gt(const char* s1, const char* s2) {
    return (strcmp(s1, s2) > 0);
}
bool string_le(const char* s1, const char* s2) {
    return (strcmp(s1, s2) <= 0);
}
bool string_ge(const char* s1, const char* s2) {
    return (strcmp(s1, s2) >= 0);
}