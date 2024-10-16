package shorts;


/*
    The task is about sending the message which is given as String, and maxLines as 2 param
    The message has to be sent in specific format:
    <mes_number> ...message ... </mes_number>
    and this entire line has to be less than maxLine

    so if we have mess="Earth is flat and will spin till it gets enough velocity to run out of Solar System" , maxLine=20
    <1> some 13 chars, 20 - 7 = 13 </1>

    <1>Earth is flat</1>
    <2> and will spin</2>
    <3> till it gets</3>
    <4> enough velocit</4>
    <5>y to run out o</5>
    <6>f Solar System</6>


 */
public class SendingMessage {

    // count first xml tag itself
    // string.toCharArray().length and iterate through
    // if the maxLine = xml tag + joining Char which are iterated
}
