from django.shortcuts import render

def index(request):
    #latest_question_list = Stop.objects.order_by('-stop_number')[:2]
    context = {'MyVar': 0}
    return render(request, 'Schedule/index.html', context)