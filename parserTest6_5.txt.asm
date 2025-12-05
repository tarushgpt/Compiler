# @author Tarush Gupta
# @date 2025-12-04

	.data
	newline: .asciiz "\n"
	varx: .word 0
	vary: .word 0
	vari: .word 0
	varsum: .word 0
	
	.text
	.globl __start
__start:
	#block
	#block
	#readln
	li $v0, 5
	syscall
	la $t0 varx
	sw $v0 ($t0)
	#assignment: y
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 vary
	sw $v0 ($t0)
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	#writeln
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	mul $v0, $v0, $t0
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#condition
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	ble $t0 $v0 else1
	#block
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#writeln
	#variable: y
	la $t0 vary
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#if else statement
	j endif1
else1:
	#block
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
endif1:
	#assignment: x
	#number
	li $v0, 0
	la $t0 varx
	sw $v0 ($t0)
	#while loop
startwhile2:
	#condition
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 10
	lw $t0 ($sp)
	addu $sp $sp 4
	bge $t0 $v0 endwhile2
	#block
	#writeln
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	#assignment: x
	#binary operand
	#variable: x
	la $t0 varx
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varx
	sw $v0 ($t0)
	j startwhile2
endwhile2:
	#assignment: sum
	#number
	li $v0, 0
	la $t0 varsum
	sw $v0 ($t0)
	#for loop
	#number
	li $v0, 1
	la $t0 vari
	sw $v0 ($t0)
startfor3:
	la $t0 vari
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#number
	li $v0, 3
	lw $t0 ($sp)
	addu $sp $sp 4
	bgt $t0 $v0 endfor3
	#block
	#assignment: sum
	#binary operand
	#variable: sum
	la $t0 varsum
	lw $v0 ($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	#variable: i
	la $t0 vari
	lw $v0 ($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0, $t0, $v0
	la $t0 varsum
	sw $v0 ($t0)
	#writeln
	#variable: sum
	la $t0 varsum
	lw $v0 ($t0)
	move $a0, $v0
	li $v0, 1
	syscall
	la $a0, newline
	li $v0, 4
	syscall
	la $t0 vari
	lw $v0 ($t0)
	addi $v0 $v0 1
	la $t0 vari
	sw $v0 ($t0)
	j startfor3
endfor3:
	li $v0, 10
	syscall
	
