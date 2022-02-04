# NumberConv
Project

About
We’re all quite used to our good old decimal system of numerals. But let’s not forget that there are other ways to count! Whether we need to convert numbers from one system to another just for fun or to store large data more efficiently, a converter proves helpful. In this project, you will create your own tool that will help you convert numbers from one system to another.

Learning outcomes
You will master loops and functions, learn about numeric data types, and explore different numeral systems including binary — one of the basic concepts in programming.

Description
Fractional numbers can also be converted from one base to another, so let's add this functionality to our program!

To convert a fractional number from one base to another, you need to split the number into two parts: integer and fractional. Convert each part from their base to decimal independently and then convert them (once again, separately) to the target base. Finally, combine both parts and get the final result!

The most challenging part will probably be converting the decimal fractional part to the target base. Don't worry, though: since you already know how to convert fractions from decimal to binary, it should not be a problem for you! Converting fractions from decimal to any base is practically the same: just use the proper denominator, which is the target base, instead of 2.

The example below shows how to convert the number 0.375 from decimal to base 20:

Fractional	Integer
0.375 * 20 =	7 + 0.5
0.5 * 20 =	10 + 0.0
Result: 0.375_{10} = 0.7a_{20}0.375 
10
​
 =0.7a 
20
​
 

Just like in the previous stage, the input numbers can be large. You might want to consider using java.math.BigDecimal to handle large fractions.

Objectives
Your program should behave almost the same way as in the previous stage: the two-level menu and the commands stay the same.

When your program gets a fractional number, it should output its representation in the target base rounded to 5 characters (digits or letters) in the fractional part. If there is no fractional part in the initial number, it should be omitted in the resulting number, too.
