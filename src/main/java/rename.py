import os

def rename_files_in_categories(base_path):
    categories = ["Grass", "Town", "Water"]
    renaming_info = {}

    # Iterar sobre las categorías
    for index, category in enumerate(categories, start=1):
        category_path = os.path.join(base_path, category)
        if os.path.exists(category_path) and os.path.isdir(category_path):
            # Obtener la ruta inicial
            start_path = os.path.abspath(category_path)
            file_list = os.listdir(category_path)

            # Renombrar los archivos dentro de la carpeta
            for i, filename in enumerate(file_list, start=1):
                old_file_path = os.path.join(category_path, filename)
                if os.path.isfile(old_file_path):
                    new_filename = f"{index}_{i}.png"  # Cambia el formato según sea necesario
                    new_file_path = os.path.join(category_path, new_filename)
                    os.rename(old_file_path, new_file_path)

            # Obtener la ruta final
            end_path = os.path.abspath(category_path)
            renaming_info[category] = (start_path, end_path)

    return renaming_info

# Cambia esta ruta por la ruta base de tu proyecto
base_path = './'

# Ejecutar la función y mostrar el resultado
renaming_result = rename_files_in_categories(base_path)
for category, (start, end) in renaming_result.items():
    print(f"{category}: {start} -> {end}")
