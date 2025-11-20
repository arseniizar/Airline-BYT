import os
from pathlib import Path

ROOT_DIRECTORY = '.'
OUTPUT_FILENAME = 'project_code.txt'
EXTENSIONS_TO_INCLUDE = ['*.java', '*.gradle']
FOLDERS_TO_EXCLUDE = ['build', '.git', '.idea', 'out']

def create_code_archive(root_dir, output_file, extensions, exclude_folders):
    print(f"🚀 Починаємо збір коду з директорії: '{os.path.abspath(root_dir)}'")

    root_path = Path(root_dir)
    source_files = []

    for ext in extensions:
        source_files.extend(root_path.rglob(ext))

    filtered_files = [
        f for f in source_files
        if not any(excluded in f.parts for excluded in exclude_folders)
    ]

    if not filtered_files:
        print("⚠️ Увага: Не знайдено жодного вихідного файлу. Перевірте, чи правильно вказано директорію та розширення.")
        return

    print(f"✅ Знайдено {len(filtered_files)} файлів для обробки.")

    try:
        with open(output_file, 'w', encoding='utf-8') as outfile:
            for file_path in sorted(filtered_files):
                print(f"   -> Додаємо файл: {file_path}")

                outfile.write('=' * 40 + " FILE: " + str(file_path.as_posix()) + ' ' + '=' * 40 + '\n\n')

                try:
                    content = file_path.read_text(encoding='utf-8')
                    outfile.write(content)
                    outfile.write('\n\n\n')
                except Exception as e:
                    error_message = f"// ПОМИЛКА: Не вдалося прочитати файл {file_path}. Причина: {e}\n\n"
                    outfile.write(error_message)
                    print(f"   [!] Помилка при читанні файлу {file_path}: {e}")

        print("\n" + "*" * 50)
        print(f"🎉 Успіх! Весь код було записано у файл: '{output_file}'")
        print("*" * 50)

    except Exception as e:
        print(f"\n❌ Критична помилка під час запису у вихідний файл: {e}")

if __name__ == '__main__':
    create_code_archive(ROOT_DIRECTORY, OUTPUT_FILENAME, EXTENSIONS_TO_INCLUDE, FOLDERS_TO_EXCLUDE)