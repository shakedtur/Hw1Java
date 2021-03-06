package components;

enum Status {CREATION1, COLLECTION2, BRANCH_STORAGE3, HUB_TRANSPORT4, HUB_STORAGE5, BRANCH_TRANSPORT6, DELIVERY7, DISTRIBUTION8, DELIVERED9};

    /*1.3.	Status – enum שכולל רשימת סטטוסים התואמים לשלבי המשלוח. השלבים הם:
יצרית חבילה (CREATION) – סטטוס התחלתי של כל חבילה שנוצרת.
איסוף (COLLECTION) – סטטוס זה חבילה מקבלת כאשר רכב הובלה נשלח לאסוף אותה מכתובת השולח.
אחסון בסניף (BRANCH_STORAGE) – החבילה שנאספה מלקוח הגיעה לסניף המקומי  של השולח.
העברה למרכז מיון (HUB_TRANSPORT) – החבילה בדרך מהסניף המקומי למרכז מיון.
אחסון במרכז מיון (HUB_STORAGE) – החבילה הגיעה למרכז מיון וממתינה להעברה לסניף היעד.
העברה לסניף היעד (BRANCH_TRANSPORT) – החבילה בדרך ממרכז מיון לסניף היעד.
נכונות למסירה (DELIVERY) – החבילה הגיעה לסניף היעד ומוכנה למסירה ללקוח הסופי.
חלוקה (DISTRIBUTION) – החבילה בדרך מסניף היעד ללקוח הסופי.
נמסר (DELIVERED) – החבילה נמסרה ללקוח הסופי.
*/
