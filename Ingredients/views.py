from django.shortcuts import render
from .models import ingredient

def index(request):
    ingredient_list = ingredient.objects.order_by('-ingredient_name')[:2]
    context = {'Ingredients': ingredient_list}
    return render(request, 'Ingredients/index.html', context)